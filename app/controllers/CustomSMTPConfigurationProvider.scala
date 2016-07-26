package controllers

import javax.inject.Provider

import play.api.{Configuration, Environment}
import play.api.inject.Module
import play.api.libs.mailer.SMTPConfiguration

/**
  * Created by carlos on 26/07/16.
  */
class CustomSMTPConfigurationProvider extends Provider[SMTPConfiguration] {
  override def get(): SMTPConfiguration = new SMTPConfiguration("Typesafe.org", 1234)
}

class CustomMailerConfigurationModule extends Module {
  def bindings(environment: Environment, configuration: Configuration) = Seq(
    bind[SMTPConfiguration].toProvider[CustomSMTPConfigurationProvider]
  )
}
