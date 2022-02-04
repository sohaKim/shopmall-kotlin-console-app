package screen

import LINE_DIVIDER
import data.CartItems

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
    }
}
