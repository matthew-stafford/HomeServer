import React from 'react';

class FileManagerAddForm extends React.Component {
	
	constructor(props) {
		super(props);
		this.state = {fileLocation:''};
		
		this.handleChange = this.handleChange.bind(this);
		this.handleSubmit = this.handleSubmit.bind(this);

	}
	
	handleChange(event) {    this.setState({fileLocation: event.target.value});  }
	
	handleSubmit(event) {
		// save data
		const response = fetch("/api/fileLocations", {
		    method: "POST", 
		    headers: {
		      "Content-Type": "application/json"
		    },
		    body: JSON.stringify( {fileLocation: this.state.fileLocation})
	    })
		.then(resp => resp.json())
		.then(
	      (result) => {
			console.log(result);
			},
	      (error) => {
			console.log(error);
	      }
	    );

		// close modal
		document.getElementById('closeFileManagerAddModal').click();

		// prevent page submit
		event.preventDefault();
  	}

	render() {

		return (
			<div >
				<button type="button" className="btn btn-primary" data-bs-toggle="modal" data-bs-target="#fileManagerAddModal">
					Add file location
				</button>
				<form onSubmit={this.handleSubmit}>   
					<div className="modal fade" id="fileManagerAddModal" tabIndex="-1" aria-labelledby="fileManagerAddModalLabel" aria-hidden="true">
						<div className="modal-dialog">
					   		<div className="modal-content">
					      		<div className="modal-header">
					        		<h5 className="modal-title" id="fileManagerAddModalLabel" >Add file location</h5>
					        		<button type="button" className="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
					      		</div>
					      		<div className="modal-body">    
					          		File location:
					          		<input type="text" name="fileLocation" value={this.state.fileLocation} onChange={this.handleChange}/>      
			     				</div>
		     					<div className="modal-footer">
		        					<button type="button" id="closeFileManagerAddModal" className="btn btn-secondary" data-bs-dismiss="modal">Close</button>
		        					<button type="button" type="submit" className="btn btn-primary">Save</button>
		      					</div>
		    				</div>
		  				</div>
					</div>
				</form>
			</div>
    	);
	}
}

export default FileManagerAddForm;