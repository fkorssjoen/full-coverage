package org.trygvekor

import grails.test.mixin.Mock
import grails.test.mixin.TestFor
import grails.web.servlet.mvc.GrailsParameterMap
import org.grails.web.json.JSONObject
import org.trygvekor.travel.HotSalesSearchService
import spock.lang.Specification

/**
 * See the API for {@link grails.test.mixin.web.ControllerUnitTestMixin} for usage instructions
 */
@TestFor(TravelSearchController)
class TravelSearchControllerSpec extends Specification {

    def setup() {
    }

    def cleanup() {
    }

    void "no params, empty result"() {
        when:
            controller.index()
            JSONObject json = new JSONObject(response.text)
        then:"fix me"
            json.size() == 0
    }

    void "search for a specific place on earth"(){
        given:
            params.placeTo = "Bogota"
            params.placeFrom = "Santiago de Chile"

            def hotSalesServiceMock = Mock(HotSalesSearchService)
            hotSalesServiceMock.getByDestination((Map) params) >> {Map params -> [[destination: "Bogota", depart: "2016-04-26", return: "2016-05-26"]]}
            controller.hotSalesSearchService = hotSalesServiceMock
        when:
            controller.index()
            JSONObject json = new JSONObject(response.text)
        then:
            json.size() > 0
            json.hot_sales.size() > 0

    }
}
