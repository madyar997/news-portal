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
    console.log(user);
    this.handleCommentSubmit = this.handleCommentSubmit.bind(this);
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
    var newComments = this.state.comments;
    console.log(request)
    const response = await commentAPI.post(`/${this.props.location.state.news.id}/${this.props.location.state.user.id}`, request)
    newComments.push(response.data);
    this.setState({comments:newComments});
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
        <CommentGroup comments={this.state.comments} handleCommentSubmit={this.handleCommentSubmit} />
      </div>
    )
  }

}

export default NewsView;