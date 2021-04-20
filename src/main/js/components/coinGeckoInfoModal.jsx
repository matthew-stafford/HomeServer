import React, {useState} from 'react';

class CoinGeckoInfoModal extends React.Component {
	constructor(props) {
		super(props);
		this.state = {
			geckoId: this.props.geckoId,
			isLoaded: false,
			data: [],
			firstLoad: false
			
		};
	}	
	
	componentDidUpdate(prevProps) {
		if (!this.state.firstLoad || prevProps.geckoId !== this.props.geckoId) {
			this.setState({firstLoad: true, isLoaded: false});
			this.fetchData();
		}
	}
		
	fetchData = () => {
		let url = "https://api.coingecko.com/api/v3/coins/"+this.props.geckoId+"?tickers=true&market_data=true&community_data=true&developer_data=true&sparkline=true";
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
	
	render() {
		return (
	  
				<div className="modal fade" id="geckoInfoModal" tabIndex="-1" aria-labelledby="geckoInfoModalLabel" aria-hidden="false">
					<div className="modal-dialog">
				   		<div className="modal-content">
				      		<div className="modal-header">
				        		<h5 className="modal-title" id="geckoInfoModalLabel" >{this.state.isLoaded ? <img src={this.state.data.image.small} width="32" height="32" />: ''} {this.props.geckoId}</h5>
				        		<button type="button" className="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
				      		</div>
				      		<div className="modal-body">    
								 {
									
									
									this.state.isLoaded ?
										<p>{this.state.data.description.en}</p>
										
										
										:
										'Loading data..'
						
									}
		     				</div>
		    					<div className="modal-footer">
		       					<button type="button" id="closeGeckoModal" className="btn btn-secondary" data-bs-dismiss="modal">Close</button>
		       					<button type="button" type="submit" className="btn btn-danger">Delete coin</button>
		     					</div>
		   				</div>
		 				</div>
				</div>
	   	);
	}
}

export default CoinGeckoInfoModal;