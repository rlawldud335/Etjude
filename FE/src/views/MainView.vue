<template lang="">
    <div class="main" >
        <div class="main__main-layout">
            <router-view></router-view>
        </div>
    </div>
</template>

<script>
export default {
    
}
</script>

<style lang="scss">
.main {
    display: flex;
    flex-direction: row;
    justify-content: center;
}

.main__main-layout {
    background-color: pink;
    width: 1136px;
    height: 100px;
}
</style>