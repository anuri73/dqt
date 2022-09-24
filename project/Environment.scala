import sbt.librarymanagement.Developer

import java.net.URL

object Environment {

  lazy val developers: List[Developer] = {

    val ujEmail = "sonkei@ya.ru"

    def genUrl(m: String) = new URL(s"mailto:$m")

    List(
      Developer("UJ", "Urmat Jenaliev", ujEmail, genUrl(ujEmail))
    )
  }
}
