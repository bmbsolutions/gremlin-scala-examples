/**
  * Created by vibro on 13.02.2017.
  */
trait AccountRepository extends Repository[Account] {

}

class AccountRepositoryImpl extends RepositoryAbstract[Account](classOf[Account]) with AccountRepository {
}