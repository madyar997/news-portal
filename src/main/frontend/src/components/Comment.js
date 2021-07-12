import { Component } from "react";

class Comment extends Component {
    constructor(props) {
        super(props);
    }
    render() {
        const { body } = this.props.comment;
        console.log(this.props)
        return (
            <div className='comment card mb-2'>
                <div className='card-body'>
                    <strong>{this.props.comment.user}</strong>  {this.props.comment.createdDate}<br />{body}
                </div>
            </div>
        )
    }
}
export default Comment;