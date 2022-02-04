package screen

import LINE_DIVIDER

class ShoppingHome : Screen() { // Screen() 상속
    /*
     * 쇼핑몰 프로그램의 시작 위치
     */
    fun start() {
        showWelcomeMessage()
        showCategories()
    }
    /*
     * 인사말 출력
     */
    fun showWelcomeMessage() {
        // 스택에 저장
        ScreenStack.push(this) // this: ShoppingHome 클래스(현재 클래스)

        println("안녕하세요, Shopmall 에 오신 것을 환영합니다!")
        println("쇼핑을 계속 하시려면 이름을 입력해 주세요:")

        val name = readLine() // 화면에서 사용자 입력받기 함수

        println(
            """
            감사합니다. 반갑습니다. $name 님
            원하시는 카테고리를 입력해주세요.
            $LINE_DIVIDER
        """.trimIndent())
    }
    private fun showCategories(){
        val shoppingCategory = ShoppingCategory() // 객체 생성

        shoppingCategory.showCategories()
    }
}
