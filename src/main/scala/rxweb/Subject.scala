package org.rxweb

import rx.lang.scala.subjects.AsyncSubject

class rxweb$Subject[A<:rxweb$Task] {
  private val subject = AsyncSubject[A]()
  subject.publish

  def next(value: A) { subject.onNext(value) }

  def observable = subject
}

object rxweb$Subject {
  def apply[A<:rxweb$Task]() = new rxweb$Subject[A]
}
