package website.lolegrand.myapplication

internal class MyApplicationRepositoryProd : MyApplicationRepository {

    override fun getMyApplicationString(): String = "Hello World from Prod !!!"

}