package com.toygar.tasks.util

abstract class StringUtil {
    companion object {

        fun camelCaseToTitleCase(camelCase: String): String {
            var titleCase = ""

            var first = true
            camelCase.forEach {
                if(first){
                    titleCase += it.toUpperCase()
                    first = false
                }else{
                    if(it.isLowerCase()){
                        titleCase += it
                    }else{
                        titleCase = "$titleCase $it"
                    }
                }
            }
            return titleCase
        }
    }
}