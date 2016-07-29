package controllers

import java.io.File
import javax.inject.Inject

import org.apache.commons.mail.EmailAttachment
import play.api.Environment
import play.api.libs.mailer._
import play.api.mvc.{Action, Controller}


/**
  * Created by carlos on 26/07/16.
  */
class ApplicationScala @Inject()(mailer: MailerClient, environment:Environment) extends Controller {

  def send = Action {
    val cid = "1234"
    val email = Email(
      "Simple email",
      "Mister FROM <cssainz77@gmail.com>",
      Seq("Miss TO <carlos.sainz@globalsign.com>"),
      // Adds attachment
      attachments = Seq(
        AttachmentFile("favicon.png", new File(environment.classLoader.getResource("public/images/favicon.png").getPath), contentId = Some(cid)),
        AttachmentData("data.txt", "data".getBytes, "text/plain", Some("Simple data"), Some(EmailAttachment.INLINE))
      ),
      // Sends text, HTML or both ...
      bodyText = Some("A text message"),
      bodyHtml = Some(s"""<html><body><p>An <b>html</b> message with cid <img src="cid:$cid"></p></body></html>""")
    )
    val id = mailer.send(email)
    Ok(s"Email $id sent!")
  }

  def sendWithCustomMailer = Action {
    val mailer = new SMTPMailer(SMTPConfiguration("typesafe.org", 1234))
    val id = mailer.send(Email("Simple email", "Mister FROM <from@email.com>"))
    Ok(s"Email $id sent!")
  }

}
