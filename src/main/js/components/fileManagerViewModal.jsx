import React, {useState} from 'react';

class FileManagerViewModal extends React.Component {
	constructor(props) {
		super(props);
		this.state = {
			fileLocation: this.props.fileLocation,
			parentData: this.props.parentData,
			isLoaded: false,
			data: []		
		};
		
		this.handleDelete = this.handleDelete.bind(this);
	}	
	
	
	handleDelete = () => {
		console.log(this.state.parentData);
		// delete this.state.fileLocation
		let url = null;
		for (let i = 0 ; i < this.state.parentData._embedded.fileLocations.length; i++) {
			if (this.state.parentData._embedded.fileLocations[i].fileLocation == this.state.fileLocation) {
				url = this.state.parentData._embedded.fileLocations[i]._links.fileLocation.href;
				break;
			}
		}
		
		// for some reason the response is an error on a successful delete
		if (url !== null) {
			const response = fetch(url, {
			    method: "DELETE", 
			    headers: {
			      "Content-Type": "application/json"
			    },
			    body: JSON.stringify( {fileLocation: this.state.fileLocation})
		    }).then(resp => resp.json())
			.then (
				(result) => {
					this.props.parentFetchData();
				},
				(error) => {
					this.props.parentFetchData();
				}
			);
		}
		
		// close modal		
		document.getElementById("closeFileManagerViewModal").click(); 
	}
	
	componentDidUpdate(prevProps) {
		if (prevProps.fileLocation !== this.props.fileLocation) {
			this.setState({data: [], isLoaded: false, fileLocation: this.props.fileLocation, parentData: this.props.parentData});
			this.fetchData(this.props.fileLocation);
		}
	}
		
	fetchData = (fileLocation) => {
		if (fileLocation != null) {
			let url = "/api/files";
			fetch(url)
		    .then(res => res.json())
		    .then(
		      (result) => {	
				console.log(result);
			    this.setState({
					data: result,
					isLoaded: true
				});
		
		      },
		      (error) => {
				console.log(error);
		        this.setState({
					isLoaded:true
				})
		      }
		    )
		}
	}
	
	render() {
		return (
	  
				<div className="modal fade" id="fileManagerViewModal" tabIndex="-1" aria-labelledby="fileManagerViewModalLabel" aria-hidden="false">
					<div className="modal-dialog">
				   		<div className="modal-content">
				      		<div className="modal-header">
				        		{
									this.state.isLoaded ? 
										<p>Loading</p>
										: null 
								}
				        		<button type="button" className="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
				      		</div>
				      		<div className="modal-body">    
								{
									
									
										
								}
		     				</div>
		    					<div className="modal-footer">
		       					<button type="button" id="closeFileManagerViewModal" className="btn btn-secondary" data-bs-dismiss="modal">Close</button>
		       					<button type="button" onClick={this.handleDelete} type="submit" className="btn btn-danger">Delete file location</button>
		     					</div>
		   				</div>
		 				</div>
				</div>
	   	);
	}
}

export default FileManagerViewModal;