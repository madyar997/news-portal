import './App.css';
import { BrowserRouter as Router, Route, Switch } from 'react-router-dom';
import React, { useEffect, useState } from 'react';
import newsApi from '../api/newsAPI';
import Header from "./Header";
import AddNews from "./AddNews";
import NewsList from './NewsList';
import EditNews from './EditNews';
import login from './login';
import register from './register';
import authHeader from '../services/authHeader';
import NewsView from './NewsView';
import Profile from './Profile';
import AdminPanel from './admin/AdminPanel';
import AboutUs from './AboutUs';
import UserManagement from './admin/UserManagement';
import axios from 'axios';
import EditUser from './admin/EditUser';


function App() {
  const [newsList, setNews] = useState([]);
  const [userList, setUsers] = useState([]);

  const retrieveUsers = async () => {
    const response = await axios.get('http://localhost:8080/admin/users', { headers: authHeader() });
    return response.data;
  }

  const retrieveNews = async () => {
    const response = await newsApi.get('/all');
    return response.data;
  }


  const addNewsHandler = async (news) => {
    console.log(news);
    const request = {
      ...news
    }
    console.log(request);
    const response = await newsApi.post('/add', request, { headers: authHeader() });
    setNews([...newsList, response.data])
  }

  const removeUserHandler = async (id) => {
    await axios.delete(`http://localhost:8080/admin/users/delete/${id}`, { headers: authHeader() });
    getAllNews();
    window.location.reload();
    // const newUserList = userList.filter((user) => {
    //   return user.id !== id;
    // });
    // setUsers(newUserList);
  }


  const removeNewsHandler = async (id) => {
    await newsApi.delete(`/delete/${id}`, { headers: authHeader() });
    const newNewsList = newsList.filter((news) => {
      return news.id !== id;
    });
    setNews(newNewsList);
  }

  const updateUserHandler = async (user) => {
    console.log(user);
    console.log("inside update method")
    // const response = await axios.put(`/edit/${news.id}`, news, { headers: authHeader() });
    // const { id, title, text, createdDate } = response.data;
    // setNews(
    //   newsList.map((news) => {
    //     return news.id === id ? { ...response.data } : news;
    //   })
    // );
  };

  const getAllNews = async () => {
    const allNews = await retrieveNews();
    if (allNews) setNews(allNews);
  };

  const updateNewsHandler = async (news) => {
    const response = await newsApi.put(`/edit/${news.id}`, news, { headers: authHeader() });
    const { id, title, text, createdDate } = response.data;
    setNews(
      newsList.map((news) => {
        return news.id === id ? { ...response.data } : news;
      })
    );
  };


  useEffect(() => {
    const getAllNews = async () => {
      const allNews = await retrieveNews();
      if (allNews) setNews(allNews);
    };

    const getAllUsers = async () => {
      const allUsers = await retrieveUsers();
      if (allUsers) setUsers(allUsers);
    }

    getAllNews();
    getAllUsers();

  }, []);



  return (
    <div className='ui container'>
      <Router>
        <Header />
        <Switch>
          <Route exact path='/'
            render={(props) => (
              <NewsList
                {...props}
                newsList={newsList}
                getNewsId={removeNewsHandler}
              />
            )} />
          <Route path='/add'
            render={(props) => <AddNews
              {...props}
              addNewsHandler={addNewsHandler} />} />


          <Route
            path='/admin/user/edit'
            render={(props) => (
              <EditUser
                {...props}
                updateUserHandler={updateUserHandler}
              />
            )}
          />
          <Route
            path='/edit'
            render={(props) => (
              <EditNews
                {...props}
                updateNewsHandler={updateNewsHandler}
              />
            )}
          />

          <Route exact path="/NewsView" component={NewsView} />
          <Route exact path="/profile" component={Profile} />
          <Route exact path="/login" component={login} />
          <Route exact path="/register" component={register} />
          <Route exact path="/admin" component={AdminPanel} />
          <Route exact path="/about" component={AboutUs} />
          <Route exact path="/admin/user-management"
            render={(props) => (
              <UserManagement
                {...props}
                users={userList}
                getUserId={removeUserHandler} />
            )} />

        </Switch>
      </Router>
    </div>
  );
}

export default App;
