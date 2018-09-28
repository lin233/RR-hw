var gulp = require('gulp'),
    cssmin = require('gulp-clean-css'),
    imagemin = require('gulp-imagemin');

gulp.task('testCssmin', function () {
    gulp.src('src/css/*.css')
        .pipe(cssmin({
            advances: false,
            compatibility: 'ie7',
            keepBreaks: true,
            keepSpecialComments: '*'
        }))
        .pipe(gulp.dest('dist/css'));
});

gulp.task('testImagemin', function () {
    gulp.src('src/img/*.{png,jpg,gif,ico,webp}')
        .pipe(imagemin({
            optimizationLevel: 5,
            progressive: true,
            interlaced: true,
            multipass: true
        }))
        .pipe(gulp.dest('dist/img'));
});