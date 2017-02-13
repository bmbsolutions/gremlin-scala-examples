import org.apache.tinkerpop.gremlin.orientdb.OrientGraphFactory
import gremlin.scala._

trait Repository[E <: Entity] {
  def findByGuid(guid: String): E
}

abstract class RepositoryAbstract[E <: Entity](entityClass: Class[E])
                                              (implicit m: Manifest[E]) extends Repository[E] {

  val graph = new OrientGraphFactory(s"memory:test-${math.random}").getNoTx().asScala

  override def findByGuid(guid: String): E = {
    val guidKey = Key[String]("guid")

    val vx = graph.V.has(guidKey, guid).headOption()
    vx match {
      case None =>
      case Some(v) => v.toCC[E] // This is the problematic part...
    }

  }
}