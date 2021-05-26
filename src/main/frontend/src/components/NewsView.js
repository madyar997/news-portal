import React, { Component } from 'react';
import parser from 'html-react-parser';
import CommentGroup from './CommentGroup';
import commentAPI from '../api/commentAPI';
import { StepContent } from 'semantic-ui-react';

class NewsView extends Component {

  constructor(props) {
    super(props);

    this.state = {
      comments: [
        { id: 1, author: 'Madyar', body: 'comment from frontend' }
      ]
    }
    console.log(props);
  }

  componentDidMount() {
    this.retrieveComments().then(result => this.setState({
      comments: result
    }))
    }

  //   const newComments = this.retrieveComments();
  //   console.log(newComments);
  //   this.setState({ comments: newComments })
  //   console.log(this.state.comments);
  // }

  async retrieveComments() {
    const response = await commentAPI.get('/all');
    return response.data;
  }

render() {
  return (
    <div>
      <ul>{this.props.location.state.news.id}</ul>
      <ul>{this.props.location.state.news.title}</ul>
      <ul>{parser(this.props.location.state.news.text)}</ul>
      <CommentGroup comments={this.state.comments}/>
    </div>

  )
}

}

export default NewsView;