package com.example.domain

object NameDecoratorEngine {

    val sampleNames: List<String> = listOf(
        "سلطان",
        "الأمير",
        "ليان",
        "Sultan",
        "Shadow",
        "Nova"
    )

    val quickSymbols: List<String> = listOf(
        "♛", "★", "꧁", "༒", "⚡", "✧", "『", "』", "</>", "∞", "❖", "♡", "۞", "〆"
    )

    // Arabic letters that only connect to the RIGHT (never insert Tatweel after them)
    private val nonLeftConnectingArabic = setOf(
        'ا', 'أ', 'إ', 'آ', 'د', 'ذ', 'ر', 'ز', 'و', 'ؤ', 'ة', 'ى', 'ء', 'ٱ'
    )

    private fun isArabicLetter(ch: Char): Boolean {
        return ch in '\u0621'..'\u064A' || ch in '\u0671'..'\u06D3'
    }

    // --- Arabic Transformers ---

    private fun applyTatweel(input: String, count: Int = 1): String {
        val sb = StringBuilder()
        val tatweel = "ـ".repeat(count)
        for (i in input.indices) {
            val ch = input[i]
            sb.append(ch)
            val next = input.getOrNull(i + 1)
            if (next != null && isArabicLetter(ch) && isArabicLetter(next) && ch !in nonLeftConnectingArabic) {
                sb.append(tatweel)
            }
        }
        return sb.toString()
    }

    private fun applyArabicDiacritics(input: String, pattern: List<String>): String {
        val sb = StringBuilder()
        var idx = 0
        for (ch in input) {
            sb.append(ch)
            if (isArabicLetter(ch)) {
                sb.append(pattern[idx % pattern.size])
                idx++
            }
        }
        return sb.toString()
    }

    private fun applyArabicCalligraphicVariants(input: String, withTatweel: Boolean = false): String {
        val mapped = input.map { ch ->
            when (ch) {
                'ك' -> 'ک'
                'ي' -> 'ی'
                'ة' -> 'ۃ'
                'ا' -> 'آ'
                'ه' -> 'ھ'
                else -> ch
            }
        }.joinToString("")
        return if (withTatweel) applyTatweel(mapped, 1) else mapped
    }

    private fun applyArabicDotMarks(input: String): String {
        val marks = listOf("ٰ", "ْ", "ّ", "َ", "ٌ")
        val sb = StringBuilder()
        var idx = 0
        for (i in input.indices) {
            val ch = input[i]
            sb.append(ch)
            if (isArabicLetter(ch)) {
                sb.append(marks[idx % marks.size])
                val next = input.getOrNull(i + 1)
                if (next != null && isArabicLetter(next) && ch !in nonLeftConnectingArabic) {
                    sb.append("ـ")
                }
                idx++
            }
        }
        return sb.toString()
    }

    private fun applyArabicSpaced(input: String): String {
        return input.trim().toCharArray().joinToString(" ")
    }

    // --- English Unicode Transformers (using code points safely for > 0xFFFF) ---

    private fun transformUnicode(
        input: String,
        upperStart: Int,
        lowerStart: Int,
        digitStart: Int? = null,
        exceptions: Map<Char, Int> = emptyMap()
    ): String {
        val sb = StringBuilder()
        for (ch in input) {
            val overrideCp = exceptions[ch]
            when {
                overrideCp != null -> sb.appendCodePoint(overrideCp)
                ch in 'A'..'Z' -> sb.appendCodePoint(upperStart + (ch - 'A'))
                ch in 'a'..'z' -> sb.appendCodePoint(lowerStart + (ch - 'a'))
                digitStart != null && ch in '0'..'9' -> sb.appendCodePoint(digitStart + (ch - '0'))
                else -> sb.append(ch)
            }
        }
        return sb.toString()
    }

    private fun toBoldSerif(input: String) = transformUnicode(
        input,
        upperStart = 0x1D400,
        lowerStart = 0x1D41A,
        digitStart = 0x1D7CE
    )

    private fun toBoldItalic(input: String) = transformUnicode(
        input,
        upperStart = 0x1D468,
        lowerStart = 0x1D482
    )

    private fun toBoldScript(input: String) = transformUnicode(
        input,
        upperStart = 0x1D4D0,
        lowerStart = 0x1D4EA
    )

    private fun toElegantScript(input: String) = transformUnicode(
        input,
        upperStart = 0x1D49C,
        lowerStart = 0x1D4B6,
        exceptions = mapOf(
            'B' to 0x212C,
            'E' to 0x2130,
            'F' to 0x2131,
            'H' to 0x210B,
            'I' to 0x2110,
            'L' to 0x2112,
            'M' to 0x2133,
            'R' to 0x211B,
            'e' to 0x212F,
            'g' to 0x210A,
            'o' to 0x2134
        )
    )

    private fun toFraktur(input: String) = transformUnicode(
        input,
        upperStart = 0x1D504,
        lowerStart = 0x1D51E,
        exceptions = mapOf(
            'C' to 0x212D,
            'H' to 0x210C,
            'I' to 0x2111,
            'R' to 0x211C,
            'Z' to 0x2128
        )
    )

    private fun toBoldFraktur(input: String) = transformUnicode(
        input,
        upperStart = 0x1D56C,
        lowerStart = 0x1D586
    )

    private fun toDoubleStruck(input: String) = transformUnicode(
        input,
        upperStart = 0x1D538,
        lowerStart = 0x1D552,
        digitStart = 0x1D7D8,
        exceptions = mapOf(
            'C' to 0x2102,
            'H' to 0x210D,
            'N' to 0x2115,
            'P' to 0x2119,
            'Q' to 0x211A,
            'R' to 0x211D,
            'Z' to 0x2124
        )
    )

    private fun toMonospace(input: String) = transformUnicode(
        input,
        upperStart = 0x1D670,
        lowerStart = 0x1D68A,
        digitStart = 0x1D7F6
    )

    private fun toSansBold(input: String) = transformUnicode(
        input,
        upperStart = 0x1D5D4,
        lowerStart = 0x1D5EE,
        digitStart = 0x1D7EC
    )

    private fun toSansBoldItalic(input: String) = transformUnicode(
        input,
        upperStart = 0x1D63C,
        lowerStart = 0x1D656
    )

    private fun toCircled(input: String) = transformUnicode(
        input,
        upperStart = 0x24B6,
        lowerStart = 0x24D0
    )

    private fun toNegativeCircled(input: String): String {
        val sb = StringBuilder()
        for (ch in input) {
            when (ch) {
                in 'A'..'Z' -> sb.appendCodePoint(0x1F150 + (ch - 'A'))
                in 'a'..'z' -> sb.appendCodePoint(0x1F150 + (ch - 'a'))
                else -> sb.append(ch)
            }
        }
        return sb.toString()
    }

    private fun toSquared(input: String): String {
        val sb = StringBuilder()
        for (ch in input) {
            when (ch) {
                in 'A'..'Z' -> sb.appendCodePoint(0x1F130 + (ch - 'A'))
                in 'a'..'z' -> sb.appendCodePoint(0x1F130 + (ch - 'a'))
                else -> sb.append(ch)
            }
        }
        return sb.toString()
    }

    private fun toFullwidth(input: String): String {
        val sb = StringBuilder()
        for (ch in input) {
            when (ch) {
                in '!'..'~' -> sb.appendCodePoint(0xFF01 + (ch - '!'))
                ' ' -> sb.append('\u3000')
                else -> sb.append(ch)
            }
        }
        return sb.toString()
    }

    private fun toSmallCaps(input: String): String {
        val map = mapOf(
            'a' to 'ᴀ', 'b' to 'ʙ', 'c' to 'ᴄ', 'd' to 'ᴅ', 'e' to 'ᴇ',
            'f' to 'ꜰ', 'g' to 'ɢ', 'h' to 'ʜ', 'i' to 'ɪ', 'j' to 'ᴊ',
            'k' to 'ᴋ', 'l' to 'ʟ', 'm' to 'ᴍ', 'n' to 'ɴ', 'o' to 'ᴏ',
            'p' to 'ᴘ', 'q' to 'ǫ', 'r' to 'ʀ', 's' to 'ꜱ', 't' to 'ᴛ',
            'u' to 'ᴜ', 'v' to 'ᴠ', 'w' to 'ᴡ', 'x' to 'x', 'y' to 'ʏ',
            'z' to 'ᴢ'
        )
        return input.map { ch ->
            map[ch.lowercaseChar()] ?: ch
        }.joinToString("")
    }

    private fun toGlitchSlash(input: String): String {
        val sb = StringBuilder()
        for (ch in input) {
            sb.append(ch)
            if (ch != ' ') {
                sb.append('\u0337')
            }
        }
        return sb.toString()
    }

    private fun toWaveUnderline(input: String): String {
        val sb = StringBuilder()
        for (ch in input) {
            sb.append(ch)
            if (ch != ' ') {
                sb.append('\u0332')
            }
        }
        return sb.toString()
    }

    fun generateDecorations(rawInput: String): List<DecoratedResult> {
        val clean = rawInput.trim().ifEmpty { "سلطان | Sultan" }
        val results = mutableListOf<DecoratedResult>()

        // --- ARABIC STYLES ---
        val tatweelSingle = applyTatweel(clean, 1)
        val tatweelDouble = applyTatweel(clean, 2)
        val diacriticRoyal = applyArabicDiacritics(clean, listOf("َ", "ْ", "ُ", "ِ", "ّ"))
        val diacriticSoft = applyArabicDiacritics(clean, listOf("ٰ", "ْ", "َ", "ٌ"))
        val calligraphic = applyArabicCalligraphicVariants(clean, withTatweel = true)
        val dottedRoyal = applyArabicDotMarks(clean)

        results += DecoratedResult(
            id = "ar_tatweel_royal",
            styleName = "تمديد ملكي متوازن",
            decoratedText = tatweelSingle,
            categoryTag = "عربي فخم",
            filter = DecoratorFilter.ARABIC
        )
        results += DecoratedResult(
            id = "ar_tatweel_imperial",
            styleName = "تمديد إمبراطوري مزدوج",
            decoratedText = "♛ $tatweelDouble ♛",
            categoryTag = "عربي ملكي",
            filter = DecoratorFilter.ARABIC
        )
        results += DecoratedResult(
            id = "ar_tashkeel_classic",
            styleName = "تشكيل عربي أصيل",
            decoratedText = diacriticRoyal,
            categoryTag = "عربي كلاسيك",
            filter = DecoratorFilter.ARABIC
        )
        results += DecoratedResult(
            id = "ar_calligraphy_persian",
            styleName = "الخط الأندلسي المزخرف",
            decoratedText = "۞ $calligraphic ۞",
            categoryTag = "خط فخم",
            filter = DecoratorFilter.ARABIC
        )
        results += DecoratedResult(
            id = "ar_dotted_ornate",
            styleName = "زخرفة التشكيل الممدود",
            decoratedText = dottedRoyal,
            categoryTag = "عربي مميز",
            filter = DecoratorFilter.ARABIC
        )
        results += DecoratedResult(
            id = "ar_soft_tashkeel",
            styleName = "تشكيل هادئ ناعم",
            decoratedText = "✧ $diacriticSoft ✧",
            categoryTag = "هادئ",
            filter = DecoratorFilter.ARABIC
        )
        results += DecoratedResult(
            id = "ar_arabesque_frame",
            styleName = "قوس الفخامة العربي",
            decoratedText = "﴿ $tatweelSingle ﴾",
            categoryTag = "عربي أصيل",
            filter = DecoratorFilter.ARABIC
        )
        results += DecoratedResult(
            id = "ar_spaced",
            styleName = "حروف عربية متباعدة",
            decoratedText = applyArabicSpaced(clean),
            categoryTag = "مودرن",
            filter = DecoratorFilter.ARABIC
        )

        // --- ENGLISH UNICODE STYLES ---
        val boldSerif = toBoldSerif(clean)
        val boldItalic = toBoldItalic(clean)
        val boldScript = toBoldScript(clean)
        val elegantScript = toElegantScript(clean)
        val fraktur = toFraktur(clean)
        val boldFraktur = toBoldFraktur(clean)
        val doubleStruck = toDoubleStruck(clean)
        val mono = toMonospace(clean)
        val sansBold = toSansBold(clean)
        val sansBoldItalic = toSansBoldItalic(clean)
        val smallCaps = toSmallCaps(clean)
        val circled = toCircled(clean)
        val negCircled = toNegativeCircled(clean)
        val squared = toSquared(clean)
        val fullwidth = toFullwidth(clean)
        val glitch = toGlitchSlash(clean)
        val underlined = toWaveUnderline(clean)

        results += DecoratedResult(
            id = "en_bold_script",
            styleName = "خط التوقيع الملكي (Script)",
            decoratedText = boldScript,
            categoryTag = "إنجليزي فخم",
            filter = DecoratorFilter.ENGLISH
        )
        results += DecoratedResult(
            id = "en_double_struck",
            styleName = "الخط المفرغ الأرستقراطي",
            decoratedText = doubleStruck,
            categoryTag = "إنجليزي مميز",
            filter = DecoratorFilter.ENGLISH
        )
        results += DecoratedResult(
            id = "en_bold_fraktur",
            styleName = "القوطي الفخم (Gothic)",
            decoratedText = boldFraktur,
            categoryTag = "قوي وفخم",
            filter = DecoratorFilter.ENGLISH
        )
        results += DecoratedResult(
            id = "en_fraktur",
            styleName = "القوطي الكلاسيكي",
            decoratedText = fraktur,
            categoryTag = "كلاسيكي",
            filter = DecoratorFilter.ENGLISH
        )
        results += DecoratedResult(
            id = "en_bold_serif",
            styleName = "سيريف عريض ملكي",
            decoratedText = boldSerif,
            categoryTag = "إنجليزي",
            filter = DecoratorFilter.ENGLISH
        )
        results += DecoratedResult(
            id = "en_bold_italic",
            styleName = "مائل فخم (Bold Italic)",
            decoratedText = boldItalic,
            categoryTag = "إنجليزي",
            filter = DecoratorFilter.ENGLISH
        )
        results += DecoratedResult(
            id = "en_elegant_script",
            styleName = "الخط الناعم الحريري",
            decoratedText = elegantScript,
            categoryTag = "هادئ",
            filter = DecoratorFilter.ENGLISH
        )
        results += DecoratedResult(
            id = "en_small_caps",
            styleName = "أحرف صغيرة أنيقة (Small Caps)",
            decoratedText = smallCaps,
            categoryTag = "مينيمال",
            filter = DecoratorFilter.ENGLISH
        )
        results += DecoratedResult(
            id = "en_sans_bold_italic",
            styleName = "عصري ديناميكي",
            decoratedText = sansBoldItalic,
            categoryTag = "مودرن",
            filter = DecoratorFilter.ENGLISH
        )
        results += DecoratedResult(
            id = "en_circled",
            styleName = "دوائر مضيئة",
            decoratedText = circled,
            categoryTag = "أشكال",
            filter = DecoratorFilter.ENGLISH
        )
        results += DecoratedResult(
            id = "en_neg_circled",
            styleName = "دوائر داكنة",
            decoratedText = negCircled,
            categoryTag = "أشكال",
            filter = DecoratorFilter.ENGLISH
        )
        results += DecoratedResult(
            id = "en_squared",
            styleName = "مربعات أنيقة",
            decoratedText = squared,
            categoryTag = "أشكال",
            filter = DecoratorFilter.ENGLISH
        )
        results += DecoratedResult(
            id = "en_fullwidth",
            styleName = "فيبورويف متباعد (Fullwidth)",
            decoratedText = fullwidth,
            categoryTag = "جمالية",
            filter = DecoratorFilter.ENGLISH
        )
        results += DecoratedResult(
            id = "en_underlined",
            styleName = "مسطر فاخر",
            decoratedText = underlined,
            categoryTag = "أنيق",
            filter = DecoratorFilter.ENGLISH
        )

        // --- ROYAL & AESTHETIC FRAMES (Work great for BOTH Arabic & English) ---
        val royalCore = applyTatweel(boldScript, 1)
        results += DecoratedResult(
            id = "frame_royal_crest",
            styleName = "تاج الملوك الفاخر",
            decoratedText = "꧁༒☬ $royalCore ☬༒꧂",
            categoryTag = "إطار ملكي",
            filter = DecoratorFilter.FRAMES
        )
        results += DecoratedResult(
            id = "frame_imperial_stars",
            styleName = "أجنحة النجوم الذهبية",
            decoratedText = "★彡 $tatweelSingle 彡★",
            categoryTag = "إطار فخم",
            filter = DecoratorFilter.FRAMES
        )
        results += DecoratedResult(
            id = "frame_japanese_brackets",
            styleName = "أقواس الهيبة اليابانية",
            decoratedText = "♛『 $boldSerif 』♛",
            categoryTag = "ملكي",
            filter = DecoratorFilter.FRAMES
        )
        results += DecoratedResult(
            id = "frame_diamond_calm",
            styleName = "إطار الهدوء الكريستالي",
            decoratedText = "✧ ⊹「 ${toSmallCaps(tatweelSingle)} 」⊹ ✧",
            categoryTag = "هادئ ومميز",
            filter = DecoratorFilter.FRAMES
        )
        results += DecoratedResult(
            id = "frame_crescent_velvet",
            styleName = "هلال السكون الفاخر",
            decoratedText = "☾ ╰ $elegantScript ╯ ☽",
            categoryTag = "هادئ",
            filter = DecoratorFilter.FRAMES
        )
        results += DecoratedResult(
            id = "frame_classic_ornament",
            styleName = "زخرفة الدانتيل الملكية",
            decoratedText = "•·.·´¯`·.·• $boldFraktur •·.·´¯`·.·•",
            categoryTag = "فخم",
            filter = DecoratorFilter.FRAMES
        )
        results += DecoratedResult(
            id = "frame_sword_clan",
            styleName = "شعار الأبطال (Clan)",
            decoratedText = "メ $sansBold メ",
            categoryTag = "ألعاب",
            filter = DecoratorFilter.FRAMES
        )
        results += DecoratedResult(
            id = "frame_soft_heart",
            styleName = "تأطير ناعم بالقلوب",
            decoratedText = "♡₊˚ $tatweelSingle ˚₊♡",
            categoryTag = "لطيف",
            filter = DecoratorFilter.FRAMES
        )

        // --- TECH & CYBER STYLES ---
        val cyberMono = toMonospace(tatweelSingle)
        results += DecoratedResult(
            id = "cyber_code_tag",
            styleName = "وسم المبرمجين (Dev Tag)",
            decoratedText = "</> $cyberMono ⚡",
            categoryTag = "تقني",
            filter = DecoratorFilter.CYBER
        )
        results += DecoratedResult(
            id = "cyber_hex_node",
            styleName = "عقدة السايبر (0xNode)",
            decoratedText = "0x01 // $mono",
            categoryTag = "سايبر",
            filter = DecoratorFilter.CYBER
        )
        results += DecoratedResult(
            id = "cyber_glitch_net",
            styleName = "غليتش الشبكة العصبية",
            decoratedText = "⚡ $glitch ⚡",
            categoryTag = "سايبر بانك",
            filter = DecoratorFilter.CYBER
        )
        results += DecoratedResult(
            id = "cyber_terminal_prompt",
            styleName = "سطر الأوامر (Terminal)",
            decoratedText = "[root@${sansBold.replace(" ", "_")} ~]$",
            categoryTag = "تقني",
            filter = DecoratorFilter.CYBER
        )
        results += DecoratedResult(
            id = "cyber_matrix_brackets",
            styleName = "مصفوفة البيانات",
            decoratedText = "【Ξ $mono Ξ】",
            categoryTag = "سايبر",
            filter = DecoratorFilter.CYBER
        )
        results += DecoratedResult(
            id = "cyber_binary_aura",
            styleName = "هالة الطاقة الرقمية",
            decoratedText = "⚡︎ ⌈ $sansBoldItalic ⌋ ⚡︎",
            categoryTag = "مستقبلي",
            filter = DecoratorFilter.CYBER
        )

        return results
    }
}
