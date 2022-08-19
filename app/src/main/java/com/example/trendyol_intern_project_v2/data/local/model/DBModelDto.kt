package com.example.trendyol_intern_project_v2.data.local.model

import androidx.room.TypeConverter
import com.example.trendyol_intern_project_v2.common.DBModelDetails
import com.example.trendyol_intern_project_v2.common.UIModelDetails
import com.example.trendyol_intern_project_v2.common.UIModelListing
import com.google.gson.Gson


fun UIModelDetails.toDBModelDetails():DBModelDetails{
        return DBModelDetails(
            this.id,
            this.name,
            this.description,
            this.metacritic,
            this.releaseDate,
            this.websiteURL,
            this.redditURL,
            this.genres,
            this.developers,
            this.backgroundImage
        )
    }

fun DBModelDetails.toUIModelDetails():UIModelDetails{
    return UIModelDetails(
        this.id,
        this.name,
        this.description,
        this.metacritic,
        this.releaseDate,
        this.websiteURL,
        this.redditURL,
        ArrayList(this.genres),
        ArrayList(this.developers),
        this.backgroundImage
    )
}

fun DBModelDetails.toUIModelListing():UIModelListing{
    return UIModelListing(
        this.id,
        this.name,
        this.backgroundImage
    )
}

