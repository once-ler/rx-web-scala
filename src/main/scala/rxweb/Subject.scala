package org.rxweb

import rx.lang.scala.subjects.BehaviorSubject

class rxweb$Subject[A<:rxweb$Task] {
  private val subject = BehaviorSubject[A]()
  subject.publish

  def next(value: A) { subject.onNext(value) }

  def observable = subject
}

object rxweb$Subject {
  def apply[A<:rxweb$Task]() = new rxweb$Subject[A]
}
