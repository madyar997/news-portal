import React, { useEffect, useState } from 'react';
import { Link } from "react-router-dom";
import parser from 'html-react-parser';
import AuthService from '../services/authService'


const NewsList = (props) => {

    const deleteNewsHandler = (id) => {
        props.getNewsId(id);
    }

    const [currentUser, setCurrentUser] = useState(null);

    useEffect(() => {
        const user = AuthService.getCurrentUser();
        setCurrentUser(user);
    }, [])


    const renderNewsList = props.newsList.map((news) => {
        console.log(currentUser);
        return (
            <div className='item' key={news.id}>
                <div className='content'>
                    <div>{news.id}</div>
                    <div className='header'>
                        <h4>
                            <Link to={{ pathname: `NewsView`, state: { news: news, user: currentUser} }}>
                                {news.title}
                            </Link>

                        </h4></div>
                    <div>{parser(news.text)}</div>
                    <div>{news.createdDate}</div>
                </div>

                {currentUser && currentUser.roles.includes('ROLE_ADMIN') ? <Link to={{ pathname: `/edit`, state: { news: news } }}>
                    <i
                        className="edit alternate outline icon"
                        style={{ color: "blue", marginTop: "7px" }}
                    ></i>
                </Link> : null}

                {currentUser && currentUser.roles.includes('ROLE_ADMIN') ?<i className='trash alternate outline icon'
                    style={{ color: 'red', marginTop: '7px' }}
                    onClick={() => deleteNewsHandler(news.id)}></i> : null}
            </div>
        )
    })

    return (
        
        <div className='ui container' style={{ paddingTop: '20px' }}>
            <h3>
                { currentUser && currentUser.roles.includes('ROLE_ADMIN') ?<Link to="/add">
                    <button className="ui button green right">Add News</button>
                </Link>: null}
            </h3>
            <b/><b/><b/>
            <div className='ui celled list'>
                {renderNewsList}
            </div>
        </div>
    );
}


export default NewsList;