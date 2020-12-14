document.addEventListener('DOMContentLoaded', () => {

    let slider = document.getElementById('slider-catalog-items');
    slider = new Swiper(`.swiper-container`, {
        loop: false,
        // loopedSlides: 2,
        // width: 0,
        // autoplay: {
        //     delay: 2000,
        //     disableOnInteraction: true
        // },
        mousewheel: {
            invert: true,
        },
        updateOnWindowResize: true,
        autoHeight: true,
        grabCursor: false,
        slidesPerView: 2,
        centeredSlides: false,
        speed: 500,
        spaceBetween: 50,
        centeredSlidesBounds: true,
        allowTouchMove: false,
        // mousewheel: true,
        pagination: {
            el: `.swiper-pagination`,
            clickable: true,
            dynamicBullets: true,
        },
        navigation: {
            nextEl: `.swiper-button-next`,
            prevEl: `.swiper-button-prev`,
        },
    });
});