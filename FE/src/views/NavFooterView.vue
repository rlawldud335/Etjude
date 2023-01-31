<template>
    <MainNav/>
    <router-view/>
    <MainFooter/>
</template>
<script>
import MainNav from '@/components/common/MainNav.vue';
import MainFooter from '@/components/common/MainFooter.vue';

export default {
    components:{
        MainNav,
        MainFooter
    }
}
</script>
