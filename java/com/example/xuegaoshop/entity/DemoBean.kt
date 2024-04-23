package com.example.xuegaoshop.entity

import java.io.Serializable

class DemoBean : Serializable {
    var articleId //话题
            = 0
    var title //话题标题
            : String? = null
    var imgid //话题图片id，本例使用静态图片
            = 0
    var time //时间
            : String? = null
    var description //描述
            : String? = null
}