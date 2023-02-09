<template>
    <div class="category">
        <div class="category-page">
            <div class="category-page__header">
                <div>카테고리</div>
            </div>
            <div class="category-page__body">
                <div class="category-page__body__list">
                    <div>전체</div>
                    <div>영화</div>
                    <div>드라마</div>
                    <div>연극</div>
                </div>
                <div class="category-page__body__contents">

                </div>
            </div>
        </div>
    </div>
</template>

<script>
export default {

}
</script>

<style lang="scss" scoped>
.category {
    width: 100%;
    display: flex;
    justify-content: center;
    align-items: center;
}

.category-page {
    width: 1136px;
    display: flex;
    flex-direction: column;
}

.category-page__header {
    width: 100%;
    font-weight: 400;
    font-size: 20px;
    padding: 30px 65px;
    box-sizing: border-box;
}

.category-page__body {
    display: flex;
    flex-direction: row;
}

.category-page__body__list {
    display: flex;
    flex-direction: column;
}

.category-page__body__list div {
    width: 200px;
    height: 40px;
    display: flex;
    justify-content: center;
    align-items: center;
}

.category-page__body__list div:hover {
    background-color: #FFE9ED;
    color: $bana-pink;
}

.category-page__body__contents {
    background-color: orange;
    width: 100%;
}
</style>