import React from 'react';

class ServicesManager extends React.Component {
	
	constructor(props) {
		super(props);
		
		this.state = {
			data: {},
			error: null,
			isLoaded: false
		}
	}
	
	
	fetchData () {
		fetch("/api/services")
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
	
	render() {
		return (			
			<div id="services">
				<div className="card">
				  <div className="card-body">
				    <h5 className="card-title">Services</h5>
	
					<table className="table table-condensed">
						<thead>
							<tr>
						    	<th scope="col">Name</th>
							</tr>
						</thead>
						<tbody>
							{
								this.state.isLoaded ? 
									this.state.data.serverServiceBeans.map( (item) => 
										<tr key={item.port}>
											<td>
												<img width="22" height="22" src={'http://'+document.location.hostname+':'+item.port+(item.favicon != null && item.favicon.startsWith('/') ? '' : '/')+item.favicon}/><a target="_blank" href={'http://'+document.location.hostname+':'+item.port}>{item.name}</a>
											</td>
										</tr>
									 ) : ''
							}
						</tbody>
					</table>				    

				    <a href="#" className="btn btn-primary">Add manually</a>
				  </div>
				</div>
			</div>
		)
	}
	
}

export default ServicesManager;