import React, { useState, useEffect, Component } from "react";
import './LandingPage.css';
import Iframe from 'react-iframe';

function LandingPage(){

        document.body.style = 'background: #7A00B2;';
        // document.body.style.overflow = "hidden"
        // document.body.classList.add("no-scroll")
        return (

            <div className="App-bg">
                
                <div id='wrapper'>
<h1 className="main-title">Welcome</h1>

                <div className="Introduction">
                <text>Hello! Welcome to a website dedicated to the modern Luddite. This term is not to be confused with someone against technology–hopefully proven by my using a website to convey my thoughts on what Neo-Luddism stands for–but, rather, a person who simply wants to prioritize ethics in the current technological shift humanity is experiencing. On the right, you will find an anthology I wrote that examines how the term Luddite has been misconstrued in history and why ensuring ethical standards are met in technological industries are more crucial to our world than ever before. For readers who may be looking for a short summary of the entire anthology, reading the first four paragraphs from the title “The Neverending Chase of Advancement: How Human Nature has led us to Manufacture a Divine Power” to the first primary source under the header “Luddism” will be enough background information to take full advantage of the rest of this website. On the news tab, you will find articles that expand on the Data Religion and discuss the massive impact our actions, whether intentional or accidental, have and how they might jeopardize equity for individuals or large groups of people. Thank you, and, if you take anything away, please remember that Luddism has nothing to do with the rate of technological advancement, but with ensuring proper precautions and equitable practices are employed.</text>
                </div>

                <div className="Doc">
                <Iframe
                allowtransparency="true"
        src="https://docs.google.com/document/d/e/2PACX-1vQBKJKOAI-EM2GfNLCml4ozkxl3POVAUk-shrFzMtIrVlNLvnWdyXHZNHG_P6REmObTUe2XUl1ufsQ_/pub?embedded=true"
        width="900px"
        height="1000px"
        id="myId"
        className="myClassname"
        display="flex"
        text
        align="right"
        position="relative"
        frameborder="10"
        style="background: #7A00B2;"
      ></Iframe></div>
      </div>
            </div>
        );
        }
        export default LandingPage;