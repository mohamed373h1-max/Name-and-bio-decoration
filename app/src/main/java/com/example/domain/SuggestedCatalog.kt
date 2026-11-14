package com.example.domain

import kotlin.random.Random

object SuggestedCatalog {

    val suggestedNames: List<SuggestedDecoratedName> = listOf(
        // --- عبارات ملكي وفخمة ---
        SuggestedDecoratedName(
            id = "sn_royal_1",
            decoratedName = "♛『 سُـلـطـانُ الـمَـجـدِ 』♛",
            styleSubtitle = "تمديد ملكي • أقواس الهيبة",
            category = BioCategory.ROYAL_LUXURY,
            isArabic = true
        ),
        SuggestedDecoratedName(
            id = "sn_royal_2",
            decoratedName = "꧁༒☬ 𝓢𝓸𝓿𝓮𝓻𝓮𝓲𝓰𝓷 ☬༒꧂",
            styleSubtitle = "تاج الملوك • خط التوقيع",
            category = BioCategory.ROYAL_LUXURY,
            isArabic = false
        ),
        SuggestedDecoratedName(
            id = "sn_royal_3",
            decoratedName = "۞ ﴿ الأَمـيـرُ الفَـخـمُ ﴾ ۞",
            styleSubtitle = "أرابيسك ملكي • تشكيل أصيل",
            category = BioCategory.ROYAL_LUXURY,
            isArabic = true
        ),
        SuggestedDecoratedName(
            id = "sn_royal_4",
            decoratedName = "★彡 𝕽𝖔𝖞𝖆𝖑_𝕮𝖗𝖔𝖜𝖓 彡★",
            styleSubtitle = "أجنحة النجوم • الخط القوطي",
            category = BioCategory.ROYAL_LUXURY,
            isArabic = false
        ),
        SuggestedDecoratedName(
            id = "sn_royal_5",
            decoratedName = "♛ مَـلِـكُ الـصَّـمـتِ ♛",
            styleSubtitle = "تمديد مزدوج • فخامة عربية",
            category = BioCategory.ROYAL_LUXURY,
            isArabic = true
        ),
        SuggestedDecoratedName(
            id = "sn_royal_6",
            decoratedName = "♛ 𝔻𝕠𝕦𝕓𝕝𝕖_𝕂𝕚𝕟𝕘 ♛",
            styleSubtitle = "الخط المفرغ الأرستقراطي",
            category = BioCategory.ROYAL_LUXURY,
            isArabic = false
        ),
        SuggestedDecoratedName(
            id = "sn_royal_7",
            decoratedName = "❖ كِـبـرِيـاءُ الـفَـوارِسِ ❖",
            styleSubtitle = "زخرفة نادرة • حضور مهيوب",
            category = BioCategory.ROYAL_LUXURY,
            isArabic = true
        ),
        SuggestedDecoratedName(
            id = "sn_royal_8",
            decoratedName = "꧁༒ 𝓛𝓸𝓻𝓭_𝓐𝓻𝓲𝓼𝓽𝓸 ༒꧂",
            styleSubtitle = "توقيع أرستقراطي فخم",
            category = BioCategory.ROYAL_LUXURY,
            isArabic = false
        ),
        SuggestedDecoratedName(
            id = "sn_royal_9",
            decoratedName = "♛『 تَـاجُ الوَقَـارِ 』♛",
            styleSubtitle = "خط أندلسي ممدود",
            category = BioCategory.ROYAL_LUXURY,
            isArabic = true
        ),
        SuggestedDecoratedName(
            id = "sn_royal_10",
            decoratedName = "★ 𝑨𝒍_𝑴𝒂𝒍𝒊𝒌_𝑿 ★",
            styleSubtitle = "سيريف مائل فاخر",
            category = BioCategory.ROYAL_LUXURY,
            isArabic = false
        ),

        // --- عبارات هادئة ومميزة ---
        SuggestedDecoratedName(
            id = "sn_calm_1",
            decoratedName = "✧ ⊹「 سَـحَـابٌ هَـادِئٌ 」⊹ ✧",
            styleSubtitle = "إطار الهدوء الكريستالي",
            category = BioCategory.CALM_UNIQUE,
            isArabic = true
        ),
        SuggestedDecoratedName(
            id = "sn_calm_2",
            decoratedName = "☾ ╰ 𝓢𝓲𝓵𝓮𝓷𝓽_𝓜𝓸𝓸𝓷 ╯ ☽",
            styleSubtitle = "هلال السكون • خط حريري",
            category = BioCategory.CALM_UNIQUE,
            isArabic = false
        ),
        SuggestedDecoratedName(
            id = "sn_calm_3",
            decoratedName = "• 「 هُـدوءُ الـمَـطَـرِ 」 •",
            styleSubtitle = "مينيمال ناعم • تشكيل خفيف",
            category = BioCategory.CALM_UNIQUE,
            isArabic = true
        ),
        SuggestedDecoratedName(
            id = "sn_calm_4",
            decoratedName = "✧ 𝑠𝑡𝑖𝑙𝑙_𝑤𝑎𝑡𝑒𝑟𝑠 ✧",
            styleSubtitle = "حروف هادئة • نجوم ناعمة",
            category = BioCategory.CALM_UNIQUE,
            isArabic = false
        ),
        SuggestedDecoratedName(
            id = "sn_calm_5",
            decoratedName = "♡₊˚ قَـمَـرٌ مَـعـزولٌ ˚₊♡",
            styleSubtitle = "تأطير ناعم • طابع شاعري",
            category = BioCategory.CALM_UNIQUE,
            isArabic = true
        ),
        SuggestedDecoratedName(
            id = "sn_calm_6",
            decoratedName = "☾ 𝑁𝑜𝑐𝑡𝑢𝑟𝑛𝑒_𝑆𝑜𝑢𝑙 ☽",
            styleSubtitle = "رقيق وعميق • ليلي",
            category = BioCategory.CALM_UNIQUE,
            isArabic = false
        ),
        SuggestedDecoratedName(
            id = "sn_calm_7",
            decoratedName = "✧ نَـسـيـمُ الفَـجـرِ ✧",
            styleSubtitle = "تمديد خفيف • صفاء",
            category = BioCategory.CALM_UNIQUE,
            isArabic = true
        ),
        SuggestedDecoratedName(
            id = "sn_calm_8",
            decoratedName = "• ᴍɪᴅɴɪɢʜᴛ_ᴅʀᴇᴀᴍ •",
            styleSubtitle = "Small Caps أنيق",
            category = BioCategory.CALM_UNIQUE,
            isArabic = false
        ),
        SuggestedDecoratedName(
            id = "sn_calm_9",
            decoratedName = "╰ عُـزلَـةٌ أَنـيـقَـةٌ ╯",
            styleSubtitle = "قوس السكينة",
            category = BioCategory.CALM_UNIQUE,
            isArabic = true
        ),
        SuggestedDecoratedName(
            id = "sn_calm_10",
            decoratedName = "✧ 𝐿𝑢𝑛𝑎𝑟_𝑊ℎ𝑖𝑠𝑝𝑒𝑟 ✧",
            styleSubtitle = "همس القمر • ناعم",
            category = BioCategory.CALM_UNIQUE,
            isArabic = false
        ),

        // --- عبارات تقنية وسايبر ---
        SuggestedDecoratedName(
            id = "sn_tech_1",
            decoratedName = "</> سَـايـبَـر_إِكـس ⚡",
            styleSubtitle = "وسم المبرمجين • طاقة",
            category = BioCategory.TECH_CYBER,
            isArabic = true
        ),
        SuggestedDecoratedName(
            id = "sn_tech_2",
            decoratedName = "0x01 // 𝙽𝚎𝚘𝚗_𝙶𝚑𝚘𝚜𝚝",
            styleSubtitle = "عقدة السايبر • Monospace",
            category = BioCategory.TECH_CYBER,
            isArabic = false
        ),
        SuggestedDecoratedName(
            id = "sn_tech_3",
            decoratedName = "【Ξ شِـفـرَةُ_الـمُـسـتَـقـبَـلِ Ξ】",
            styleSubtitle = "مصفوفة البيانات",
            category = BioCategory.TECH_CYBER,
            isArabic = true
        ),
        SuggestedDecoratedName(
            id = "sn_tech_4",
            decoratedName = "⚡︎ ⌈ 𝑪𝒚𝒃𝒆𝒓_𝑷𝒖𝒍𝒔𝒆 ⌋ ⚡︎",
            styleSubtitle = "هالة الطاقة الرقمية",
            category = BioCategory.TECH_CYBER,
            isArabic = false
        ),
        SuggestedDecoratedName(
            id = "sn_tech_5",
            decoratedName = "[root@نُـخـبَـة ~]$",
            styleSubtitle = "سطر الأوامر (Terminal)",
            category = BioCategory.TECH_CYBER,
            isArabic = true
        ),
        SuggestedDecoratedName(
            id = "sn_tech_6",
            decoratedName = "</> 𝕭𝖎𝖓𝖆𝖗𝖞_𝕬𝖗𝖈𝖍𝖎𝖙𝖊𝖈𝖙",
            styleSubtitle = "معمار الأكواد",
            category = BioCategory.TECH_CYBER,
            isArabic = false
        ),
        SuggestedDecoratedName(
            id = "sn_tech_7",
            decoratedName = "0xFF // مِـعـمَـارُ_الأَكـوادِ",
            styleSubtitle = "سايبر عربي حديث",
            category = BioCategory.TECH_CYBER,
            isArabic = true
        ),
        SuggestedDecoratedName(
            id = "sn_tech_8",
            decoratedName = "⚡ N̷e̷u̷r̷a̷l̷_̷N̷o̷d̷e̷ ⚡",
            styleSubtitle = "غليتش الشبكة العصبية",
            category = BioCategory.TECH_CYBER,
            isArabic = false
        ),
        SuggestedDecoratedName(
            id = "sn_tech_9",
            decoratedName = "【Ξ 𝚃𝚎𝚌𝚑_𝙿𝚑𝚊𝚗𝚝𝚘𝚖 Ξ】",
            styleSubtitle = "شبح التقنية",
            category = BioCategory.TECH_CYBER,
            isArabic = false
        ),
        SuggestedDecoratedName(
            id = "sn_tech_10",
            decoratedName = "</> خَـوارِزْمِـيُّ_الـغَـدِ ⚡",
            styleSubtitle = "هندسة المستقبل",
            category = BioCategory.TECH_CYBER,
            isArabic = true
        )
    )

    val readyAboutTemplates: List<ReadyAboutTemplate> = listOf(
        ReadyAboutTemplate(
            id = "about_royal_1",
            title = "نبذة ملكية مقتضبة",
            content = "♛ الاسم: [اكتب اسمك]\n❖ المبدأ: لا أُقارن نفسي بأحد، أنا النسخة الأصلية.\n★ الاهتمامات: الفخامة • الطموح • التميز",
            category = BioCategory.ROYAL_LUXURY,
            platformHint = "إنستقرام • تيك توك",
            isArabic = true
        ),
        ReadyAboutTemplate(
            id = "about_royal_2",
            title = "بطاقة حضور فخمة (EN)",
            content = "♛ [Your Name] | Sovereign Mindset\n✦ Crafting my legacy in silence.\n❖ Quality over noise — always.",
            category = BioCategory.ROYAL_LUXURY,
            platformHint = "Instagram • X",
            isArabic = false
        ),
        ReadyAboutTemplate(
            id = "about_royal_3",
            title = "نبذة قيادية راقية",
            content = "﴿ هدوءٌ يُخفي شغفاً لا ينطفئ ﴾\n♛ أؤمن بأن التفاصيل تصنع الفارق.\n📍 مدينة الأحلام والطموح",
            category = BioCategory.ROYAL_LUXURY,
            platformHint = "واتساب • تيليجرام",
            isArabic = true
        ),
        ReadyAboutTemplate(
            id = "about_calm_1",
            title = "نبذة هادئة وشاعرية",
            content = "✧ [اسمك هنا]\n☾ محبٌّ للقهوة، الهدوء، والموسيقى الخافتة.\n⊹ أبحث عن الجمال في التفاصيل البسيطة.",
            category = BioCategory.CALM_UNIQUE,
            platformHint = "إنستقرام • سناب",
            isArabic = true
        ),
        ReadyAboutTemplate(
            id = "about_calm_2",
            title = "Minimalist Calm Bio (EN)",
            content = "✧ [Your Name]\n☾ Slow mornings • Deep thoughts • Quiet growth\n⊹ Protecting my peace daily.",
            category = BioCategory.CALM_UNIQUE,
            platformHint = "Instagram • Threads",
            isArabic = false
        ),
        ReadyAboutTemplate(
            id = "about_calm_3",
            title = "نبذة عزلة أنيقة",
            content = "╰ روحٌ هادئة في عالمٍ صاخب ╯\n• قراءة • تأمل • سكون الليل ✧\nما يُلامس الروح يبقى.",
            category = BioCategory.CALM_UNIQUE,
            platformHint = "تيليجرام • X",
            isArabic = true
        ),
        ReadyAboutTemplate(
            id = "about_tech_1",
            title = "نبذة مطوّر وتقني سايبر",
            content = "</> [اسمك] • مهندس أفكار رقمية ⚡\n0x01 // أحوّل القهوة إلى شيفرات برمجية.\n【Ξ Status: Building the future Ξ】",
            category = BioCategory.TECH_CYBER,
            platformHint = "GitHub • X • LinkedIn",
            isArabic = true
        ),
        ReadyAboutTemplate(
            id = "about_tech_2",
            title = "Cyber Dev Card (EN)",
            content = "0x01 // [Your Handle] ⚡\n</> Code • AI • Cyber Aesthetics\n[root@universe ~]$ create_magic --loop",
            category = BioCategory.TECH_CYBER,
            platformHint = "GitHub • Discord",
            isArabic = false
        ),
        ReadyAboutTemplate(
            id = "about_tech_3",
            title = "نبذة قيمر وتقني محترف",
            content = "⚡ [اسمك / اللقب] • Cyber Player\n</> تقنية • ألعاب • تصميم مستقبلي\n∞ لا حدود للتطوير المستمر.",
            category = BioCategory.TECH_CYBER,
            platformHint = "Discord • Twitch",
            isArabic = true
        )
    )

    val ageRecommendations: List<AgeRecommendationItem> = listOf(
        // 13 - 17
        AgeRecommendationItem(
            id = "age_teen_1",
            text = "メ 𝓝𝓮𝓸𝓷_𝓚𝓲𝓭 ⚡",
            label = "اسم مزخرف • سايبر شبابي",
            ageGroup = AgeGroup.TEENS,
            category = BioCategory.TECH_CYBER,
            isName = true
        ),
        AgeRecommendationItem(
            id = "age_teen_2",
            text = "★彡 نَـجـمُ الـقِـيـمـرز 彡★",
            label = "لقب ألعاب وحماس",
            ageGroup = AgeGroup.TEENS,
            category = BioCategory.ROYAL_LUXURY,
            isName = true
        ),
        AgeRecommendationItem(
            id = "age_teen_3",
            text = "✧ ᴘɪxᴇʟ_ᴅʀᴇᴀᴍᴇʀ ✧",
            label = "اسم هادئ وعصري",
            ageGroup = AgeGroup.TEENS,
            category = BioCategory.CALM_UNIQUE,
            isName = true
        ),
        AgeRecommendationItem(
            id = "age_teen_4",
            text = "Leveling up in real life & games ⚡ </>",
            label = "بايو مناسب لجيل الناشئين",
            ageGroup = AgeGroup.TEENS,
            category = BioCategory.TECH_CYBER,
            isName = false
        ),

        // 18 - 25
        AgeRecommendationItem(
            id = "age_youth_1",
            text = "♛『 عَـزِيـزُ الـنَّـفـسِ 』♛",
            label = "اسم شبابي فخم",
            ageGroup = AgeGroup.YOUTH,
            category = BioCategory.ROYAL_LUXURY,
            isName = true
        ),
        AgeRecommendationItem(
            id = "age_youth_2",
            text = "0x01 // 𝚅𝚒𝚜𝚒𝚘𝚗_𝕏 ⚡",
            label = "اسم تقني حديث",
            ageGroup = AgeGroup.YOUTH,
            category = BioCategory.TECH_CYBER,
            isName = true
        ),
        AgeRecommendationItem(
            id = "age_youth_3",
            text = "☾ 𝐿𝑢𝑛𝑎𝑟_𝑆𝑜𝑢𝑙 ☽",
            label = "اسم هادئ ومميز",
            ageGroup = AgeGroup.YOUTH,
            category = BioCategory.CALM_UNIQUE,
            isName = true
        ),
        AgeRecommendationItem(
            id = "age_youth_4",
            text = "شَغوفٌ ببناء مستقبلي، أعيش التفاصيل بهدوء وثقة ♛",
            label = "بايو شبابي طموح",
            ageGroup = AgeGroup.YOUTH,
            category = BioCategory.ROYAL_LUXURY,
            isName = false
        ),

        // 26 - 35
        AgeRecommendationItem(
            id = "age_adult_1",
            text = "♛ 𝓢𝓸𝓿𝓮𝓻𝓮𝓲𝓰𝓷_𝓜𝓲𝓷𝓭 ♛",
            label = "اسم يعكس النضج والفخامة",
            ageGroup = AgeGroup.ADULTS,
            category = BioCategory.ROYAL_LUXURY,
            isName = true
        ),
        AgeRecommendationItem(
            id = "age_adult_2",
            text = "</> مِـعـمَـارُ_الـفِـكـرِ ⚡",
            label = "اسم تقني احترافي",
            ageGroup = AgeGroup.ADULTS,
            category = BioCategory.TECH_CYBER,
            isName = true
        ),
        AgeRecommendationItem(
            id = "age_adult_3",
            text = "• 「 هُـدوءُ الـواثِـقِ 」 •",
            label = "اسم راقٍ وهادئ",
            ageGroup = AgeGroup.ADULTS,
            category = BioCategory.CALM_UNIQUE,
            isName = true
        ),
        AgeRecommendationItem(
            id = "age_adult_4",
            text = "أقدّر الوقت، الجودة، والعلاقات الصادقة فقط. ❖",
            label = "بايو متزن وعميق",
            ageGroup = AgeGroup.ADULTS,
            category = BioCategory.CALM_UNIQUE,
            isName = false
        ),

        // 36+
        AgeRecommendationItem(
            id = "age_mature_1",
            text = "۞ ﴿ وِقَـارُ الـحَـكـيـمِ ﴾ ۞",
            label = "خط عربي كلاسيكي أصيل",
            ageGroup = AgeGroup.MATURE,
            category = BioCategory.ROYAL_LUXURY,
            isName = true
        ),
        AgeRecommendationItem(
            id = "age_mature_2",
            text = "✧ سَـكـيـنَـةُ الـرُّوحِ ✧",
            label = "اسم هادئ وقور",
            ageGroup = AgeGroup.MATURE,
            category = BioCategory.CALM_UNIQUE,
            isName = true
        ),
        AgeRecommendationItem(
            id = "age_mature_3",
            text = "♛ 𝑻𝒊𝒎𝒆𝒍𝒆𝒔𝒔_𝑬𝒍𝒆𝒈𝒂𝒏𝒄𝒆 ♛",
            label = "اسم إنجليزي كلاسيكي فخم",
            ageGroup = AgeGroup.MATURE,
            category = BioCategory.ROYAL_LUXURY,
            isName = true
        ),
        AgeRecommendationItem(
            id = "age_mature_4",
            text = "الخبرة تُعلّمك أن القوة الحقيقية في الهدوء والاتزان. ۞",
            label = "عبارة حكمة ووقار",
            ageGroup = AgeGroup.MATURE,
            category = BioCategory.CALM_UNIQUE,
            isName = false
        )
    )

    private val royalArabicRoots = listOf(
        "سلطان المجد", "أمير الصمت", "فخر الملوك", "تاج الكبرياء",
        "هيبة الفارس", "سيف العز", "عزيز النفس", "شموخ القمم",
        "ملهم العصر", "الأسطورة الملكية"
    )
    private val royalEnglishRoots = listOf(
        "Sovereign", "RoyalCrown", "GoldenEmperor", "NobleKnight",
        "MajesticKing", "VelvetMonarch", "ImperialAura", "GrandSultan"
    )

    private val calmArabicRoots = listOf(
        "سحاب هادئ", "نسيم الفجر", "قمر الليل", "همس المطر",
        "سكون الروح", "نجم السكينة", "عزلة أنيقة", "ظل الغيم",
        "صفاء القلب", "وتر هادئ"
    )
    private val calmEnglishRoots = listOf(
        "SilentMoon", "LunarWhisper", "StillWaters", "SoftHorizon",
        "CalmVelvet", "MidnightEcho", "SereneSoul", "CloudDrifter"
    )

    private val techArabicRoots = listOf(
        "سايبر إكس", "معمار الأكواد", "شبح الشبكة", "خوارزمي الغد",
        "نبض النيون", "شفرة المستقبل", "مهندس السايبر", "ذئب البيانات",
        "عقل الماتريكس", "نخبة السيرفر"
    )
    private val techEnglishRoots = listOf(
        "CyberPulse", "NeonGhost", "BinaryKing", "RootPhantom",
        "QuantumNode", "CodeArchitect", "ZeroDayX", "MatrixRunner"
    )

    fun generateRandomNicknames(
        categoryFilter: BioCategory?,
        languageFilter: LanguageFilter,
        count: Int = 4
    ): List<GeneratedNickname> {
        val targetCats = if (categoryFilter != null) listOf(categoryFilter) else BioCategory.entries
        val generated = mutableListOf<GeneratedNickname>()

        repeat(count) { idx ->
            val cat = targetCats[Random.nextInt(targetCats.size)]
            val useArabic = when (languageFilter) {
                LanguageFilter.ARABIC -> true
                LanguageFilter.ENGLISH -> false
                LanguageFilter.ALL -> Random.nextBoolean()
            }

            val root = when (cat) {
                BioCategory.ROYAL_LUXURY -> if (useArabic) royalArabicRoots.random() else royalEnglishRoots.random()
                BioCategory.CALM_UNIQUE -> if (useArabic) calmArabicRoots.random() else calmEnglishRoots.random()
                BioCategory.TECH_CYBER -> if (useArabic) techArabicRoots.random() else techEnglishRoots.random()
            }

            val allStyles = NameDecoratorEngine.generateDecorations(root)
            val matchingStyles = when (cat) {
                BioCategory.ROYAL_LUXURY -> allStyles.filter {
                    it.filter == DecoratorFilter.FRAMES ||
                        it.id.contains("royal") ||
                        it.id.contains("imperial") ||
                        it.id.contains("script") ||
                        it.id.contains("fraktur") ||
                        it.id.contains("double_struck")
                }
                BioCategory.CALM_UNIQUE -> allStyles.filter {
                    it.id.contains("calm") ||
                        it.id.contains("soft") ||
                        it.id.contains("crescent") ||
                        it.id.contains("small_caps") ||
                        it.id.contains("elegant") ||
                        it.id.contains("tatweel_royal")
                }
                BioCategory.TECH_CYBER -> allStyles.filter {
                    it.filter == DecoratorFilter.CYBER
                }
            }.ifEmpty { allStyles }

            val picked = matchingStyles.random()
            generated += GeneratedNickname(
                id = "rand_${System.nanoTime()}_$idx",
                text = picked.decoratedText,
                category = cat,
                styleLabel = "${cat.label} • ${picked.styleName}"
            )
        }
        return generated
    }

    val decorativeSymbols: List<DecorativeSymbolItem> = listOf(
        // Royal & Crowns
        DecorativeSymbolItem("sym_r_1", "♛", "تاج ملكي", SymbolCategory.ROYAL),
        DecorativeSymbolItem("sym_r_2", "♔", "تاج أبيض", SymbolCategory.ROYAL),
        DecorativeSymbolItem("sym_r_3", "⚜", "زهرة الملكية", SymbolCategory.ROYAL),
        DecorativeSymbolItem("sym_r_4", "۞", "أرابيسك", SymbolCategory.ROYAL),
        DecorativeSymbolItem("sym_r_5", "❖", "معيّن فاخر", SymbolCategory.ROYAL),
        DecorativeSymbolItem("sym_r_6", "★", "نجمة ممتلئة", SymbolCategory.ROYAL),
        DecorativeSymbolItem("sym_r_7", "✪", "نجمة التاج", SymbolCategory.ROYAL),
        DecorativeSymbolItem("sym_r_8", "✦", "بريق رباعي", SymbolCategory.ROYAL),
        DecorativeSymbolItem("sym_r_9", "☬", "شعار القوة", SymbolCategory.ROYAL),
        DecorativeSymbolItem("sym_r_10", "༒", "زخرفة كلاسيك", SymbolCategory.ROYAL),
        DecorativeSymbolItem("sym_r_11", "۝", "فاصلة عربية", SymbolCategory.ROYAL),
        DecorativeSymbolItem("sym_r_12", "✯", "نجمة حادة", SymbolCategory.ROYAL),

        // Frames & Brackets (Compatible with Arabic & English)
        DecorativeSymbolItem("sym_f_1", "『  』", "أقواس يابانية", SymbolCategory.FRAMES, true, "『", "』"),
        DecorativeSymbolItem("sym_f_2", "「  」", "إطار ناعم", SymbolCategory.FRAMES, true, "「", "」"),
        DecorativeSymbolItem("sym_f_3", "【  】", "أقواس عريضة", SymbolCategory.FRAMES, true, "【", "】"),
        DecorativeSymbolItem("sym_f_4", "﴿  ﴾", "قوس عربي", SymbolCategory.FRAMES, true, "﴿", "﴾"),
        DecorativeSymbolItem("sym_f_5", "꧁  ꧂", "أجنحة الملوك", SymbolCategory.FRAMES, true, "꧁", "꧂"),
        DecorativeSymbolItem("sym_f_6", "★彡  彡★", "أجنحة النجوم", SymbolCategory.FRAMES, true, "★彡", "彡★"),
        DecorativeSymbolItem("sym_f_7", "╰  ╯", "قوس هادئ", SymbolCategory.FRAMES, true, "╰", "╯"),
        DecorativeSymbolItem("sym_f_8", "⌈  ⌋", "زوايا سايبر", SymbolCategory.FRAMES, true, "⌈", "⌋"),
        DecorativeSymbolItem("sym_f_9", "♛  ♛", "إطار التاج", SymbolCategory.FRAMES, true, "♛", "♛"),
        DecorativeSymbolItem("sym_f_10", "メ  メ", "إطار الأبطال", SymbolCategory.FRAMES, true, "メ", "メ"),
        DecorativeSymbolItem("sym_f_11", "«  »", "أقواس مزدوجة", SymbolCategory.FRAMES, true, "«", "»"),
        DecorativeSymbolItem("sym_f_12", "•·.·´¯`·.·•", "إطار دانتيل", SymbolCategory.FRAMES, true, "•·.·´¯`·.·•", "•·.·´¯`·.·•"),

        // Calm & Stars
        DecorativeSymbolItem("sym_c_1", "✧", "نجمة هادئة", SymbolCategory.CALM),
        DecorativeSymbolItem("sym_c_2", "⊹", "وميض ناعم", SymbolCategory.CALM),
        DecorativeSymbolItem("sym_c_3", "☾", "هلال ليلي", SymbolCategory.CALM),
        DecorativeSymbolItem("sym_c_4", "☽", "هلال مقابل", SymbolCategory.CALM),
        DecorativeSymbolItem("sym_c_5", "♡", "قلب رقيق", SymbolCategory.CALM),
        DecorativeSymbolItem("sym_c_6", "₊˚", "بريق علوي", SymbolCategory.CALM),
        DecorativeSymbolItem("sym_c_7", "˚₊", "بريق جانبي", SymbolCategory.CALM),
        DecorativeSymbolItem("sym_c_8", "✿", "زهرة ناعمة", SymbolCategory.CALM),
        DecorativeSymbolItem("sym_c_9", "❄", "ندفة ثلج", SymbolCategory.CALM),
        DecorativeSymbolItem("sym_c_10", "☁", "سحابة", SymbolCategory.CALM),
        DecorativeSymbolItem("sym_c_11", "∞", "لانهاية", SymbolCategory.CALM),
        DecorativeSymbolItem("sym_c_12", "⋆", "نقطة نجمية", SymbolCategory.CALM),

        // Tech & Cyber
        DecorativeSymbolItem("sym_t_1", "</>", "وسم برمجي", SymbolCategory.CYBER),
        DecorativeSymbolItem("sym_t_2", "⚡", "طاقة سايبر", SymbolCategory.CYBER),
        DecorativeSymbolItem("sym_t_3", "⚡︎", "برق رفيع", SymbolCategory.CYBER),
        DecorativeSymbolItem("sym_t_4", "0x01", "عقدة ثنائية", SymbolCategory.CYBER),
        DecorativeSymbolItem("sym_t_5", "Ξ", "رمز الماتريكس", SymbolCategory.CYBER),
        DecorativeSymbolItem("sym_t_6", "⌬", "سداسي تقني", SymbolCategory.CYBER),
        DecorativeSymbolItem("sym_t_7", "⌘", "مفتاح نظام", SymbolCategory.CYBER),
        DecorativeSymbolItem("sym_t_8", "◈", "جوهرة سايبر", SymbolCategory.CYBER),
        DecorativeSymbolItem("sym_t_9", "▣", "مربع مصفوفة", SymbolCategory.CYBER),
        DecorativeSymbolItem("sym_t_10", "//", "تعليق كود", SymbolCategory.CYBER),
        DecorativeSymbolItem("sym_t_11", "▓", "بلوك رقمي", SymbolCategory.CYBER),
        DecorativeSymbolItem("sym_t_12", "⎔", "خلية تقنية", SymbolCategory.CYBER),

        // Dividers for Bio & Profiles
        DecorativeSymbolItem("sym_d_1", "━═━♛━═━", "فاصل ملكي", SymbolCategory.DIVIDERS),
        DecorativeSymbolItem("sym_d_2", "✧──⊹──✧", "فاصل هادئ", SymbolCategory.DIVIDERS),
        DecorativeSymbolItem("sym_d_3", "⚡════</>", "فاصل سايبر", SymbolCategory.DIVIDERS),
        DecorativeSymbolItem("sym_d_4", "•────────•", "خط بسيط", SymbolCategory.DIVIDERS),
        DecorativeSymbolItem("sym_d_5", "◈━━━━━━◈", "فاصل ماسي", SymbolCategory.DIVIDERS),
        DecorativeSymbolItem("sym_d_6", "꧁───۞───꧂", "فاصل عربي", SymbolCategory.DIVIDERS)
    )
}

