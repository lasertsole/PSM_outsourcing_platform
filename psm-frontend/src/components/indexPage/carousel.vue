<template>
	<div class="swiperBox">
		<swiper class="Swiper"
			:slidesPerView="1"
			:spaceBetween="30"
			:loop="true"
			:centeredSlides="true"
			:pagination="{
				clickable: false,
                type: 'bullets',
			}"
			:autoplay="{
				delay: 2500,
				disableOnInteraction: true,
			}"
			:navigation="false"
            effect="coverflow"
			:modules="modules"
		 >
			<swiper-slide><img src="Carousel/bg-1.jpg" alt="" /></swiper-slide>
            <swiper-slide><img src="Carousel/bg-2.jpg" alt="" /></swiper-slide>
            <swiper-slide><img src="Carousel/bg-3.jpg" alt="" /></swiper-slide>
		</swiper>
	</div>
</template>

<script setup>
    import { Swiper, SwiperSlide } from 'swiper/vue'; // swiper所需组件
    // 这是分页器和对应方法，swiper好像在6的时候就已经分离了分页器和一些其他工具
    import { Autoplay, Navigation, Pagination, A11y, EffectCoverflow } from 'swiper';
    // 引入swiper样式，对应css 如果使用less或者css只需要把scss改为对应的即可
    import 'swiper/css';
    import 'swiper/css/navigation';
    import 'swiper/css/pagination';
    const modules = [Autoplay, Pagination, Navigation, A11y, EffectCoverflow ];
</script>

<style lang="scss" scoped>
    .swiperBox{
        width: 100%;
        height: 100%;
        .Swiper{
            width: 100%;
            height: 100%;
            img{
                object-fit: cover;
                width: 100%;
                height: 100%;
            }
        }
    }
</style>
<style lang="scss">
    .swiper-pagination{
        display: flex;
        justify-content: center;
        align-items: center;
        .swiper-pagination-bullet-active{
            width: 16px;
            height: 16px;
            background-image: url(icons/eatBean.svg);
            background-size: 100% 100%;
            background-position: center;
            background-repeat: no-repeat;
            background-color: transparent;
        }
    }
</style>