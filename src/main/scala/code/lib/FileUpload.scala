/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package code.lib

import net.liftweb._
import util._
import Helpers._

import common._
import http._
import sitemap._
import Loc._
import mapper._

import code.model._

import rest._
import json._
import JsonDSL._
object FileUpload extends RestHelper {
  
  serve {
    case "upload" :: "thing" :: Nil Post req => {
        println("uploaded "+req.uploadedFiles)
        
        val ojv: Box[JValue] = 
          req.uploadedFiles.map(fph => {                                       
                                ("name" -> fph.fileName) ~ 
                                ("type" -> fph.mimeType) ~
                                ("size" -> fph.length)}).headOption

        val ajv = ("name" -> "n/a") ~ ("type" -> "n/a") ~ ("size" -> 0L) ~ ("yak" -> "brrrr")

        val ret = ojv openOr ajv

        // This is a tad bit of a hack, but we need to return text/plain, not JSON
        /* val jr = JsonResponse(ret).toResponse.asInstanceOf[InMemoryResponse]
         InMemoryResponse(jr.data, ("Content-Length", jr.data.length.toString) :: 
         ("Content-Type", "text/plain") :: S.getHeaders(Nil),
         S.responseCookies, 200) */
        JsonResponse(JArray(ret::Nil))
      }
  }
}