import React from 'react';
import AddComment from './AddComment';
import Comment from './Comment';

class CommentGroup extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            comments: [
                { id: 1, body: 'this is my first comment' },
                { id: 2, body: 'this is my second comment' }
            ]
        }
        this.handleCommentSubmit = this.handleCommentSubmit.bind(this);
    }

    renderComments() {
        // const { comments } = this.state;
        console.log(this.props.comments);
       return this.props.comments.map(comment => {
            const { id, body } = comment;
            return (
               <Comment key={id} body={body}/>
            );
        });
    }

    handleCommentSubmit(data) {
        const postData = {
            comment: data
        }
        // axios.post('/')
        console.log(data);
    }
    render() {
        return (
            <div>
                {this.renderComments()}
                <AddComment handleCommentSubmit={this.handleCommentSubmit} />
            </div>
        )
    }
}

export default CommentGroup;







