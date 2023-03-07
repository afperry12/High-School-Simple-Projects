import React from 'react';
import './Support.css'

const Store = () => {
    return (
        // <div className="container">
        //     <h1 data-splitting>Become a Luddite</h1>
        //     <a href="https://github.com/EthicalML/awesome-artificial-intelligence-guidelines">Ethical Practices for Computer Science</a>
        //     <a href="http://bit.ly/ANTIRACISMRESOURCES">Anti-Racist Pieces of Work</a>
        
        // </div>

        <div id="news">
            <h1 data-splitting>Become a Luddite</h1>
            <p></p>
    <ul id="articles">
        <li class="article-item">
            <h2><p>Guidelines for proper ethical practices for computer scientists looking to create AI algorithms!</p>
            <a href="https://github.com/EthicalML/awesome-artificial-intelligence-guidelines">Ethical Practices for Computer Science</a></h2>
        
        </li>

        <li class="article-item">
        <h2><p>Guidelines and work for becoming an Anti-Racist!</p>
            <a href="http://bit.ly/ANTIRACISMRESOURCES">Becoming Anti-Racist</a></h2>
        
        </li>
        <li class="article-item">
        <h2><p>Fascinating article further supporting the argument for Luddism (incase you were not convinced yet)!</p>
            <a href="https://theconversation.com/im-a-luddite-you-should-be-one-too-163172">Support for Luddism</a></h2>
        
        </li>
    </ul>
</div>
        
    )
}
export default Store;