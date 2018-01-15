package rxweb

import rx.lang.scala.subjects.AsyncSubject

class rxweb$Subject[A>:rxweb$Task] {
  private val subject = new AsyncSubject[A]
  subject.publish

  def next(value: A) { subject.onNext(value) }

  def observable = subject.asJavaObservable[A]
}
