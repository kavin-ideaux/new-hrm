

var swiper = new Swiper(".mySwiper", {
    slidesPerView: 1,
    spaceBetween: 15,
    pagination: {
        el: ".swiper-pagination",
        clickable: true,
    },
    navigation: {
        nextEl: ".swiper-button-next",
        prevEl: ".swiper-button-prev",
    },
    breakpoints: {
        640: {
            slidesPerView: 2,
            spaceBetween: 15,
        },
        1024: {
            slidesPerView: 3,
            spaceBetween: 15,
        },
    },

    loop: true, 
    autoplay: {
        delay: 5000,
        disableOnInteraction: false,
    },
});

var swiperCarousel = new Swiper(".myCarousel", {
    navigation: {
        nextEl: ".swiper-button-next",
        prevEl: ".swiper-button-prev",
    },

    loop: true, 
    autoplay: {
        delay: 3000,
        disableOnInteraction: false,
    },
    
});

