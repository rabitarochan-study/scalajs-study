package com.github.rabitarochan.study.scalajs

import scala.language.dynamics

import scala.scalajs.js
import org.scalajs.dom
import dom.console
import com.github.rabitarochan.study.scalajs.shared._

import scala.scalajs.js.{Object, Any, JSON}
import scala.scalajs.js.annotation.{JSExportAll, JSExport}

import com.github.rabitarochan.study.scalajs.mithril.m

object ScalaJSExample extends js.JSApp {

  def main(): Unit = {

    val todo = new Todo("task", false);
    console.log(todo.task())

    m.module(dom.document.getElementById("todo-root"), new TodoModule())

  }

}

class Todo(_task: String, _done: Boolean) {
  val task = m.prop[String](_task)
  val done = m.prop[Boolean](_done)
}

class TodoList extends js.Array[Todo]

class TodoViewModel {

  val list = new js.Array[Todo]
  val text = m.prop("")

  def add(): Unit = {
    if (text() != "") {
      val newTodo = new Todo(text(), false)
      list.push(newTodo)
      text("")
    }
  }

}

class TodoController {
  new TodoViewModel()
}

class TodoModule extends mithril.Module {

  val vm = new TodoViewModel()

  override def controller: js.Object = js.Object()

  override def view: () => js.Array[js.Any] = () => {
    val textInput = m("input", json(onchange = m.withAttr("value", vm.text), checked = vm.text()))
    val submitBtn = m("button", json(onclick = vm.add), "Add")
    val todoListTable = m("table", js.Array(
      vm.list.map { task =>
        m("tr", js.Array(
          m("td", js.Array(
            m("input[type=checkbox]", json(onclick = m.withAttr("checked", task.done), checked = task.done()))
          )),
          m("td", json(style = json(textDecoration = if (task.done()) "line-through" else "none")), task.task())
        ))
      }
    ))

    console.log(textInput)

    js.Array(textInput, submitBtn, todoListTable)
  }

}