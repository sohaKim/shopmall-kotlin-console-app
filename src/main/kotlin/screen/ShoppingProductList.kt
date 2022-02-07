package screen
import LINE_DIVIDER
import data.CartItems
import data.Product
import extensions.getNotEmptyInt
import extensions.getNotEmptyString

class ShoppingProductList(private val selectedCategory: String) : Screen() { // Screen() 상속
    private val products = arrayOf(
        Product("패션", "겨울 패딩"), // art+enter => 상단에 import 기능
        Product("패션", "겨울 바지"),
        Product("전자기기", "핸드폰"),
        Product("전자기기", "블루투스 이어폰"),
        Product("전자기기", "노트북"),
        Product("반려동물용품", "건식 사료"),
        Product("반려동물용품", "습식 사료"),
        Product("반려동물용품", "치약"),
        Product("반려동물용품", "간식"),
    )

    // String: key-CategoryLabel값 저장, List<Product>: name-categoryLabel & name값 저장
    private val categories: Map<String, List<Product>> =
        products.groupBy { products ->
            products.categoryLabel

        }

    /*
     * 사용자가 입력한 카테고리 정보를 받아 해당 카테고리의 상품을 출력
     */
    fun showProducts() {
        // 스택에 저장
        ScreenStack.push(this) // this: ShoppingProductList 클래스(현재 클래스)

        // 지정된 카테고리의 상품목록을 가져온다.
        // categoryProducts= ("categoryLabel", "name")값 배열
        val categoryProducts = categories[selectedCategory]

        // 가져온 제품목록이 null,공백이 아닐경우 display("""    """)
        if (!categoryProducts.isNullOrEmpty()) {
            println(""" 
                $LINE_DIVIDER
                   선택하신 [$selectedCategory] 카테고리 상품입니다.
            """.trimIndent())
            /*
            val productSize = categoryProducts.size // 배열의 크기 변수에 리턴
            for (index in 0 until productSize) { // ex) 0번 index의 name값
                println("${index}.${categoryProducts[index].name}")
            }
            */
            categoryProducts.forEachIndexed{
                index, product->println("${index}. ${product.name}")
            }

            // 장바구니에 담을 상품 선택
            showCartOption(categoryProducts)
        } else {
            showEmptyProductMessage(selectedCategory)
        }
    }

    private fun showCartOption(categoryProducts: List<Product>) {
        println(""" 
                $LINE_DIVIDER
                   장바구니에 담을 상품 번호를 선택해 주세요.
            """.trimIndent())
        
        // 상품 번호 입력 수행
        val selectedIndex = readLine().getNotEmptyInt()
        categoryProducts.getOrNull(selectedIndex)?.let{

            product -> CartItems.addProduct(product)
            println("=> 장바구니로 이동하시려면 #을, 계속 쇼핑하려면 *를 입력해주세요!")
            val answer = readLine().getNotEmptyString()

            if (answer == "#") {
                val shoppingCart = ShoppingCart() // 객체 생성
                shoppingCart.showCartItems()
            } else if (answer == "*") {
                showProducts()
            } else {
                // 그 외 입력값(목록에 없는null)에 대한 처리
                println("잘못된 입력입니다. 다시 입력해주세요.")
                showProducts()
            }
        } ?: kotlin.run{ // null일 경우 수행, 3항연산자와 유사
            println("$selectedIndex 은 목록에 없는 상품 번호 입니다. 다시 입력해주세요!")
            showProducts() // showProducts()로 이동
        }
    }

    private fun showEmptyProductMessage(selectedCategory: String) {
        println("[$selectedCategory] 카테고리 상품이 등록되기 전입니다.")
    }
}
