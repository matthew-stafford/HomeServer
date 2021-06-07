import React from 'react';
import FileManagerAddForm from './fileManagerAddForm.jsx';
import FileManagerViewModal from './fileManagerViewModal.jsx';

class FileManager extends React.Component {
	
	constructor(props) {
		super(props);
		
		this.state = {
			data: {},
			error: null,
			isLoaded: false,
			selectedFileLocation: null
		}
	}
	
		
	fetchData () {
		fetch("/api/fileLocations")
	    .then(res => res.json())
	    .then(
	      (result) => {
	        this.setState({
	          isLoaded: true,
	          data: result
	        });
	      },
	      // Note: it's important to handle errors here
	      // instead of a catch() block so that we don't swallow
	      // exceptions from actual bugs in components.
	      (error) => {
			console.log(error);
	        this.setState({
	          isLoaded: true,
	          error
	        });
	      }
	    )
	}
	
	componentDidMount() {
		this.fetchData();
	}
	
	showFileManagerViewModal = (event) => {
		let selectedFileLocation = event.currentTarget.attributes["data-hs-filelocation"].value;
		
		this.setState({selectedFileLocation: selectedFileLocation, showInfoModal: true});
	}
	
	render() {
		return (			
			<div id="fileManager">
				<div className="card">
				  <div className="card-body">
				    <h5 className="card-title">Folder/Files</h5>
					{
						this.state.isLoaded ?
							<table className="table table-sm">
								<thead>
									<tr>
								    	<th scope="col">Location</th>
									</tr>
								</thead>
								<tbody>
									{ 
										this.state.data._embedded.fileLocations.map( (item,index) => 
											<tr key={index} data-hs-filelocation={item.fileLocation} data-bs-toggle="modal" data-bs-target="#fileManagerViewModal" onClick={this.showFileManagerViewModal}><td>{item.fileLocation}</td></tr>
										)
									}
								</tbody>
							</table>	
						: <p>No folders being shared.</p>			    
					}
				    <FileManagerAddForm parentFetchData={this.fetchData} />
				    <FileManagerViewModal parentFetchData={this.fetchData} parentData={this.state.data} fileLocation={this.state.selectedFileLocation} />
				  </div>
				</div>
			</div>
		)
	}
	
}

export default FileManager;