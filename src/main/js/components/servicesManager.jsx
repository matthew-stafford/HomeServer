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
			
			</div>
		)
	}
	
}

export default ServicesManager;