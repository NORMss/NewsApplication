package com.norm.mynewsapplication.presentation.onboarding

import androidx.annotation.DrawableRes
import com.norm.mynewsapplication.R

data class Page(
    val title: String,
    val description: String,
    @DrawableRes val image: Int,
)

val pages = listOf(
    Page(
        title = "Space Tourism: First Private Flight to Mars",
        description = "Space company “StarVoyage” has announced the launch of the first private flight to Mars. Passengers will have the opportunity to see the red planet up close and experience weightlessness.",
        image = R.drawable.onboarding1
    ),
    Page(
        title = "AI in Medicine: New Breakthrough",
        description = "Researchers from MIT have developed an artificial intelligence capable of diagnosing rare diseases with 98% accuracy. This opens new possibilities for medical diagnostics.",
        image = R.drawable.onboarding2
    ),
    Page(
        title = "Ecological Crisis: Saving the Oceans",
        description = "The global initiative “OceanGuardians” has declared the creation of a network of ocean reserves to protect marine ecosystems from pollution and overpopulation. The first reserve will be established off the coast of the Galapagos Islands.",
        image = R.drawable.onboarding3
    ),
)