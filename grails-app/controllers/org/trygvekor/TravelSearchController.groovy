package org.trygvekor

import grails.converters.JSON
import org.grails.web.json.JSONObject
import org.trygvekor.travel.HotSalesSearchService

class TravelSearchController {

    HotSalesSearchService hotSalesSearchService

    def index() {

        JSONObject result = new JSONObject()

        if(params.placeTo){
            result.hot_sales = hotSalesSearchService.getByDestination(params)
        }

        render(text: result as JSON, contentType: "application/json")

    }
}
