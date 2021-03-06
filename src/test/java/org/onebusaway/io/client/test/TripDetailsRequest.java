/*
 * Copyright (C) 2010 Paul Watts (paulcwatts@gmail.com)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.onebusaway.io.client.test;

import org.junit.Test;
import org.onebusaway.io.client.ObaApi;
import org.onebusaway.io.client.elements.ObaRegion;
import org.onebusaway.io.client.elements.ObaTrip;
import org.onebusaway.io.client.elements.ObaTripSchedule;
import org.onebusaway.io.client.elements.ObaTripStatus;
import org.onebusaway.io.client.mock.MockRegion;
import org.onebusaway.io.client.request.ObaTripDetailsRequest;
import org.onebusaway.io.client.request.ObaTripDetailsResponse;

import java.io.UnsupportedEncodingException;
import java.net.URISyntaxException;
import java.util.HashMap;

@SuppressWarnings("serial")
public class TripDetailsRequest extends ObaTestCase {

    @Test
    public void testKCMTripRequestUsingRegion() throws UnsupportedEncodingException, URISyntaxException {
        // Test by setting region
        ObaRegion ps = MockRegion.getPugetSound();
        assertNotNull(ps);
        ObaApi.getDefaultContext().setRegion(ps);
        _assertKCMTripRequest();
    }

    private void _assertKCMTripRequest() throws URISyntaxException, UnsupportedEncodingException {
        ObaTripDetailsRequest.Builder builder =
                new ObaTripDetailsRequest.Builder("1_18196913");
        ObaTripDetailsRequest request = builder.build();
        UriAssert.assertUriMatch(
                DEFAULT_BASE_URL + "api/where/trip-details/" + "1_18196913"
                        + ".json",
                new HashMap<String, String>() {{
                    put("key", "*");
                    put("version", "2");
                }},
                request
        );
    }

    @Test
    public void testKCMTripResponseUsingRegion() throws Exception {
        // Test by setting region
        ObaRegion ps = MockRegion.getPugetSound();
        assertNotNull(ps);
        ObaApi.getDefaultContext().setRegion(ps);
        _assertKCMTripResponse();
    }

    @Test
    private void _assertKCMTripResponse() {
        ObaTripDetailsResponse response =
                new ObaTripDetailsRequest.Builder("1_18196913")
                        .build()
                        .call();
        assertOK(response);
        assertEquals("1_18196913", response.getId());

        ObaTripSchedule schedule = response.getSchedule();
        assertNotNull(schedule);

        //ObaTripStatus status = response.getStatus();
        //assertNotNull(status);

        // Make sure the trip exists
        ObaTrip trip = response.getTrip(response.getId());
        assertNotNull(trip);
    }

    @Test
    public void testNoTripsRequestUsingRegion() throws UnsupportedEncodingException, URISyntaxException {
        // Test by setting region
        ObaRegion ps = MockRegion.getPugetSound();
        assertNotNull(ps);
        ObaApi.getDefaultContext().setRegion(ps);
        _assertNoTripsRequest();
    }

    private void _assertNoTripsRequest() throws UnsupportedEncodingException, URISyntaxException {
    	String id = "1_18196913_no_trip";
        ObaTripDetailsRequest request = new ObaTripDetailsRequest.Builder(id)
                .setIncludeTrip(false)
                .build();
        UriAssert.assertUriMatch(
        		DEFAULT_BASE_URL + "api/where/trip-details/" + id
                        + ".json",
                new HashMap<String, String>() {{
                    put("includeTrip", "false");
                    put("key", "*");
                    put("version", "2");
                }},
                request
        );
    }

    // TODO: API response includes the trip anyway
    @Test
    public void testNoTripsResponse() throws Exception {
    	String id = "1_18196913";
        ObaTripDetailsResponse response =
                new ObaTripDetailsRequest.Builder(id)
                        .setIncludeTrip(false)
                        .build()
                        .call();
        assertOK(response);
        assertEquals(id, response.getId());
    }

    @Test
    public void testNoScheduleRequestUsingRegion() throws UnsupportedEncodingException, URISyntaxException {
        // Test by setting region
        ObaRegion ps = MockRegion.getPugetSound();
        assertNotNull(ps);
        ObaApi.getDefaultContext().setRegion(ps);
        _assertNoScheduleRequest();
    }

    private void _assertNoScheduleRequest() throws UnsupportedEncodingException, URISyntaxException {
    	String id = "1_18196913_no_schedule";
        ObaTripDetailsRequest request = new ObaTripDetailsRequest.Builder(id)
                .setIncludeSchedule(false)
                .build();
        UriAssert.assertUriMatch(
        		DEFAULT_BASE_URL + "api/where/trip-details/" + id
                        + ".json",
                new HashMap<String, String>() {{
                    put("includeSchedule", "false");
                    put("key", "*");
                    put("version", "2");
                }},
                request
        );
    }

    @Test
    public void testNoScheduleResponse() throws Exception {
    	String id = "1_18196913_no_schedule";
        ObaTripDetailsResponse response =
                new ObaTripDetailsRequest.Builder(id)
                        .setIncludeSchedule(false)
                        .build()
                        .call();
        assertOK(response);
        assertEquals(id, response.getId());

        ObaTripSchedule schedule = response.getSchedule();
        assertNull(schedule);
    }

    @Test
    public void testNoStatusRequestUsingRegion() throws UnsupportedEncodingException, URISyntaxException {
        // Test by setting region
        ObaRegion ps = MockRegion.getPugetSound();
        assertNotNull(ps);
        ObaApi.getDefaultContext().setRegion(ps);
        _assertNoStatusRequest();
    }

    private void _assertNoStatusRequest() throws URISyntaxException, UnsupportedEncodingException {
    	String id = "1_18196913_no_status";
        ObaTripDetailsRequest request =
                new ObaTripDetailsRequest.Builder(id)
                        .setIncludeStatus(false)
                        .build();
        UriAssert.assertUriMatch(
        		DEFAULT_BASE_URL + "api/where/trip-details/" + id
                        + ".json",
                new HashMap<String, String>() {{
                    put("includeStatus", "false");
                    put("key", "*");
                    put("version", "2");
                }},
                request
        );
    }

    @Test
    public void testNoStatus() throws Exception {
    	String id = "1_18196913";
        ObaTripDetailsResponse response =
                new ObaTripDetailsRequest.Builder(id)
                        .setIncludeStatus(false)
                        .build()
                        .call();
        assertOK(response);
        assertEquals(id, response.getId());

        ObaTripStatus status = response.getStatus();
        assertNull(status);
    }

    @Test
    public void testNewRequestUsingRegion() throws UnsupportedEncodingException, URISyntaxException {
        // Test by setting region
        ObaRegion ps = MockRegion.getPugetSound();
        assertNotNull(ps);
        ObaApi.getDefaultContext().setRegion(ps);
        _assertNewRequest();
    }

    private void _assertNewRequest() throws UnsupportedEncodingException, URISyntaxException {
        // This is just to make sure we copy and call newRequest() at least once
    	String id = "1_18196913";
        ObaTripDetailsRequest request =
                ObaTripDetailsRequest.newRequest(id);
        UriAssert.assertUriMatch(
        		DEFAULT_BASE_URL + "api/where/trip-details/" + id
                        + ".json",
                new HashMap<String, String>() {{
                    put("key", "*");
                    put("version", "2");
                }},
                request
        );
    }
}
