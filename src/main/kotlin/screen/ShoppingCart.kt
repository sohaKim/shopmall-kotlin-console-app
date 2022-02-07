package screen

import LINE_DIVIDER
import data.CartItems
import extensions.getNotEmptyString

/*
 * 장바구니의 내용을 화면에 출력
 */
class ShoppingCart : Screen() { // Screen() 상속
    private val products = CartItems.products

    fun showCartItems() {
        ScreenStack.push(this) // this: ShoppingCart 클래스(현재 클래스)

        if (products.isNotEmpty()) {
            // 장바구니의 상품 목록 출력
            // CartItems--products<Product, Int>에서 키(Product)만 가져오기
            println(
                products.keys.joinToString( // .keys: 키 전체 가져오기
                    separator = ", \n",
                    prefix = """
                        $LINE_DIVIDER
                              장바구니에 담긴 상품 목록입니다.
                              
                    """.trimIndent()
                // Product class,    수량 부분은 <Product, Int>의 product를 입력하면 수량을 가져온다
                ) { product -> "카테고리: ${product.categoryLabel} / 상품명: ${product.name} / 수량: ${products[product]}"

                }
            )

        } else {
            println("""
                장바구니에 담긴 상품이 없습니다.
            """.trimIndent())
        }
        showPreviousScreenOption()
    }
    /*
     * 이전 화면으로 돌아가는 기능 구현
     */
    private fun showPreviousScreenOption() {
        println("""
            $LINE_DIVIDER
            이전 화면으로 돌아가시겠습니까? (y/n)
        """.trimIndent())
        when (readLine().getNotEmptyString()) { // StringExtensions 파일에 구현
            "y" -> {
                // 이전 화면으로 이동
                moveToPreviousScreen()

            } "n" -> { // 상품목록 보여주기
                showCartItems()
            }
            else -> {
                // 재입력 요청
                println("잘못 입력하였습니다. 다시 입력해주세요! (y/n)")
                showPreviousScreenOption()
            }
       }
    }

    private fun moveToPreviousScreen() {
        // (1) 스택에서 현재 화면을 popup 시킴
        ScreenStack.pop() // ScreenStack파일의 pop()함수 호출

        // (2) 현재 스택에서 제일 위의 클래스 확인 후 show...() 메소드를 실행,
        //     스택에서 꺼내지 않고 내용만 확인 -- ScreenStack파일의 peak()함수 호출
        when( val previousScreen = ScreenStack.peak()) {
            is ShoppingCategory -> { // ShoppingCategory의 함수 호출
                previousScreen.showCategories() // selectedCategory 필요
           }
            is ShoppingProductList -> { // ShoppingProductList의 함수 호출
                previousScreen.showProducts() // selectedCategory 필요
            }
            is ShoppingCart, is ShoppingHome -> {
                // 아무일도 하지 않음.
            }
        }

    }

}
