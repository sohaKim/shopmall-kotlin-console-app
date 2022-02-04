package screen
import extensions.getNotEmptyString

class ShoppingCategory : Screen() { // Screen() 상속
    fun showCategories() {
        /*
         * (1) 상품 카테고리 표시
         * (2) 사용자 입력받기
         * (3) 사용자가 잘못된 값 입력 처리
         */
        // 스택에 저장
        ScreenStack.push(this) // this: ShoppingCategory 클래스(현재 클래스)

        val categories = arrayOf("패션", "전자기기", "반려동물용품")
        for (category in categories) {
            println(category)
        }
        println("==> 장바구니로 이동하시려면 '#'을 입력해주세요.")

        // 카테고리 입력받기
        // readLine()의 반환타입이 String?이므로 앞에서 구현한 확장함수 사용가능
        var selectedCategory = readLine().getNotEmptyString()

        // 사용자 올바른 값을 입력할때까지 반복처리
        /*
        while (selectedCategory.isNullOrBlank()) { // null이나 빈공간이 없을때까지
            println("값을 입력해주세요!")
            selectedCategory = readLine()
        }
        */

        if (selectedCategory == "#") {
            // 장바구니로 이동
            val shoppingCart = ShoppingCart() // 객체 생성
            shoppingCart.showCartItems()

        } else {
            if (categories.contains(selectedCategory)) {
                // 카테고리 상품목록 보여주기 --- 있으면 selectedCategory 보여주기
                val shoppingProductList = ShoppingProductList() // 객체 생성
                shoppingProductList.showProducts(selectedCategory)

            } else {
                // 카테고리 목록에 없을경우
                showErrorMessage(selectedCategory)
            }
        }
    }

    private fun showErrorMessage(selectedCategory: String?) {
        println("[$selectedCategory] : 존재하지 않는 카테고리입니다. 다시 입력해주세요.")
        showCategories()
    }
}
