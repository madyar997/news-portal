import React from 'react';
import SunEditor from 'suneditor-react';
import 'suneditor/dist/css/suneditor.min.css'; // Import Sun Editor's CSS File

class EditNews extends React.Component {
    constructor(props) {
        super(props);
        const { id, title, text, createdDate } = props.location.state.news;
        this.state = {
            id,
            title,
            text,
            createdDate
        };
        this.handleChange = this.handleChange.bind(this);
    }
   
    handleChange(content){
        this.setState({text:content});
        console.log(this.state);
    }

    update = (e) => {
        e.preventDefault();
        if(this.state.title === '' || this.state.text === ''){
            alert('All the fields are mandatory');
            return;
        }
        this.props.updateNewsHandler(this.state);
        this.setState({title:'', text:'', createdDate:''});
        this.props.history.push('/');
    };

    render() {
        return (
            <div className='ui main'>
                <h2>Edit News</h2>
                <form className='ui form' onSubmit={this.update}>
                    <div className='field'>
                        <label>Title</label>
                        <input
                            type='text'
                            name='text'
                            placeholder='Title'
                            value={this.state.title}
                            onChange={(e) => this.setState({ title: e.target.value })} />
                    </div>

                    <div className='field'>
                    <label>Content</label>
                    <SunEditor height='300px' onChange={this.handleChange} defaultValue={this.state.text}/>
                </div>
                    <button className='ui button blue'>Update</button>
                </form>
            </div>
        )
    };
}

export default EditNews;