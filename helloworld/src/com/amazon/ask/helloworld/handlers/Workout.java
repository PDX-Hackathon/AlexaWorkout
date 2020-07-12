/*
     Copyright 2018 Amazon.com, Inc. or its affiliates. All Rights Reserved.

     Licensed under the Apache License, Version 2.0 (the "License"). You may not use this file
     except in compliance with the License. A copy of the License is located at

         http://aws.amazon.com/apache2.0/

     or in the "license" file accompanying this file. This file is distributed on an "AS IS" BASIS,
     WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for
     the specific language governing permissions and limitations under the License.
*/

package com.amazon.ask.helloworld.handlers;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.dispatcher.request.handler.RequestHandler;
import com.amazon.ask.model.Response;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;


import static com.amazon.ask.request.Predicates.intentName;

public class Workout implements RequestHandler {

    public Cardio cardio = new Cardio();
    public Yoga yoga = new Yoga();

    public List<String> list = new ArrayList<>();
    public Random rand = new Random();


    @Override
    public boolean canHandle(HandlerInput input) {
        return input.matches(intentName("Workout"));
    }

    @Override
    public Optional<Response> handle(HandlerInput input) {
        this.list.add(cardio.jummpingJacks);
        this.list.add(cardio.running);
        this.list.add(yoga.goatYoga);
        this.list.add(yoga.stretch);
        int randomNum = this.rand.nextInt(3);
        String workout = this.list.get(randomNum);

        String reprompt = "Would you like to set a reminder after your workout?";
        String speechText = "The workout selected to do is " + workout + " " + reprompt;
        return input.getResponseBuilder()
                .withSpeech(speechText)
                .withReprompt(speechText)
                .build();
    }

}
