import React, { Component } from 'react';
import parser from 'html-react-parser';
import CommentGroup from './CommentGroup';
import commentAPI from '../api/commentAPI';
import authHeader from '../services/authHeader';


class NewsView extends Component {

  constructor(props) {
    super(props);
    const { news } = props.location.state;
    const { user } = props.location.state;
    this.state = {
      comments: [],
    }
    console.log(news);
    console.log(user)
  }

  componentDidMount() {
    this.retrieveComments().then(result => this.setState({
      comments: result
    }))

  }

  async handleCommentSubmit(data) {
    const request = {
      body: data
    }
    console.log(request)
    const response = await commentAPI.post(`/${this.props.newsId}/${this.props.userId}`, request, { headers: authHeader() })
    this.setState({ comments: response.data })

  }
  async retrieveComments() {
    const response = await commentAPI.get(`/${this.props.location.state.news.id}`, { headers: authHeader() });
    return response.data;
  }

  render() {
    return (
      <div>
        <ul>{this.props.location.state.news.id}</ul>
        <ul>{this.props.location.state.news.title}</ul>
        <ul>{parser(this.props.location.state.news.text)}</ul>

        <CommentGroup comments={this.state.comments} news={this.props.location.state.news.id} userId={this.props.location.state.user.id} handleCommentSubmit={this.handleCommentSubmit()} />
      </div>

    )
  }

}

export default NewsView;