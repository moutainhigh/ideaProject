var gulp = require('gulp');
var uglify = require('gulp-uglify');
var pump = require('pump');
var uglifycss = require('gulp-uglifycss');
var del = require('del');


gulp.task('clean', function (cb) {
    del([
        'dist/**/*',
        // 我们不希望删掉这个文件，所以我们取反这个匹配模式
        '!dist/mobile/deploy.json'
    ], cb);
});

//复制src文件下的所有的文件到dist中
gulp.task('copy', function(cb) {
    // 将你的默认的任务代码放在这
    pump([
            gulp.src('src/**/*'),
            gulp.dest('./dist')
        ],
        cb
    );
});


//压缩src下的全部js文件复制到dist中
//mangle排除  压缩seajs
gulp.task('one',['copy'], function(cb) {
    // 将你的默认的任务代码放在这
    pump([
            gulp.src('src/**/*.js'),
            uglify({
                mangle: {except: ['require' ,'exports' ,'module' ,'$']}
                // mangle: {reserved: ['require' ,'exports' ,'module' ,'$']}
            }),
            gulp.dest('./dist')
        ],
        cb
    );
});




//压缩css
gulp.task('css',['copy'], function () {
    gulp.src('./src/**/*.css')
        .pipe(uglifycss({
            "maxLineLen": 1000,
            "uglyComments": true
        }))
        .pipe(gulp.dest('./dist'));
});

gulp.task("default",['copy','one','css']);