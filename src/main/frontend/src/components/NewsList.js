import React from 'react';
import { Link } from "react-router-dom";
import parser from 'html-react-parser';


const NewsList = (props) => {

    const deleteNewsHandler = (id) => {
        props.getNewsId(id);
    }

    const renderNewsList = props.newsList.map((news) => {
        return (
            <div className='item'>
                <div className='content'>
                    <div>{news.id}</div>
                    <div className='header'>
                        <h4>
                            <Link to={{ pathname: `NewsView`, state: { news: news } }}>
                                {news.title}
                            </Link>

                        </h4></div>
                    <div>{parser(news.text)}</div>
                    <div>{news.createdDate}</div>
                </div>
                <Link to={{ pathname: `/edit`, state: { news: news } }}>
                    <i
                        className="edit alternate outline icon"
                        style={{ color: "blue", marginTop: "7px" }}
                    ></i>
                </Link>

                <i className='trash alternate outline icon'
                    style={{ color: 'red', marginTop: '7px' }}
                    onClick={() => deleteNewsHandler(news.id)}></i>
            </div>
        )
    })

    return (
        <div className='ui container' style={{ paddingTop: '20px' }}>
            <h3>News List nn
                <Link to="/add">
                    <button className="ui button green right">Add News</button>
                </Link>
            </h3>
            <div className='ui celled list'>
                {renderNewsList}
            </div>
        </div>

    );
}


export default NewsList;