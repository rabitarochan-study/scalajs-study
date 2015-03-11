package com.github.rabitarochan.study.scalajs.mithril

import org.scalajs.dom.Element

import scala.scalajs.js

trait MithrilStatic extends js.Object {
  // factory
  def apply(tagName: String, attrs: js.Object): js.Object = js.native
  def apply(tagName: String, children: js.Array[js.Any]): js.Object = js.native
  def apply(tagName: String, attrs: js.Object, innerText: String): js.Object = js.native
  def apply(tagName: String, attrs: js.Object, children: js.Array[js.Any]): js.Object = js.native

  def module(element: Element, module: Module): js.Dynamic = js.native

  def prop[A](v: A): MithrilProp[A] = js.native

  def withAttr[A](attr: String, prop: MithrilProp[A]): js.Object = js.native
}

trait MithrilProp[A] extends js.Object {
  def apply(): A = js.native
  def apply(v: A): A = js.native
}

trait Module {
  def controller: js.Object
  def view: () => js.Array[js.Any]
}