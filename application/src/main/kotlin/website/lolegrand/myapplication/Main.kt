package website.lolegrand.myapplication

fun main() {

    val myRepository = MyApplicationRepoProvider().getMyApplicationRepo()
    println(myRepository.getMyApplicationString())

}
