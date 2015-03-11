package com.github.rabitarochan.study.scalajs

import scala.scalajs.js

package object mithril extends js.GlobalScope {
  val m: MithrilStatic = js.native
}
