import React, {useState} from 'react';

class CoinGeckoInfoModal extends React.Component {
	constructor(props) {
		super(props);
		this.state = {
			geckoId: this.props.geckoId,
			isLoaded: false,
			data: [],
			firstLoad: false,
			parentData: this.props.parentData
			
		};
		
		this.handleDelete = this.handleDelete.bind(this);
	}	
	
	
	handleDelete = () => {
		// delete this.state.geckoId
		let url = null;
		for (let i = 0 ; i < this.state.parentData._embedded.coinGeckoTickers.length; i++) {
			if (this.state.parentData._embedded.coinGeckoTickers[i].geckoId == this.state.geckoId) {
				url = this.state.parentData._embedded.coinGeckoTickers[i]._links.coinGeckoTicker.href;
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
			    body: JSON.stringify( {geckoId: this.state.geckoId})
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
		document.getElementById("closeGeckoModal").click(); 
	}
	
	componentDidUpdate(prevProps) {
		if (!this.state.firstLoad || prevProps.geckoId !== this.props.geckoId) {
			this.setState({data: [], firstLoad: true, isLoaded: false, geckoId: this.props.geckoId, parentData: this.props.parentData});
			this.fetchData(this.props.geckoId);
		}
	}
		
	fetchData = (geckoId) => {
		if (geckoId != null) {
			let url = "https://api.coingecko.com/api/v3/coins/"+geckoId+"?tickers=true&market_data=true&community_data=true&developer_data=true&sparkline=true";
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
	  
				<div className="modal fade" id="geckoInfoModal" tabIndex="-1" aria-labelledby="geckoInfoModalLabel" aria-hidden="false">
					<div className="modal-dialog">
				   		<div className="modal-content">
				      		<div className="modal-header">
				        		{
									this.state.isLoaded ? 
										<h5 className="modal-title" id="geckoInfoModalLabel" > 
											<img src={this.state.data.image.small} width="32" height="32" /> #{this.state.data.market_cap_rank} {this.state.data.name} ({this.state.data.symbol.toUpperCase()})
										</h5>
										: null 
								}
				        		<button type="button" className="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
				      		</div>
				      		<div className="modal-body">    
								 {
									
									
									this.state.isLoaded ?
										<div>
											{
												this.state.data.public_notice != null ? 
													<div className="alert alert-primary" role="alert">
													  {this.state.data.public_notice}
													</div>
												: null
												
											}
										
											<h3>Scores</h3>
											<small>Liquidity: {this.state.data.liquidity_score}<br/></small>
											<small>Public Interest: {this.state.data.public_interest_score}<br/></small>
											<small>Alexa Rank: {this.state.data.public_interest_stats.alexa_rank}<br/></small>
											<small>Community Score: {this.state.data.community_score}<br/></small>
											<small>CoinGecko Score: {this.state.data.coingeck_score}<br/></small>
											
										
											<h3>Community</h3>
											<small>Twitter Followers: {this.state.data.community_data.twitter_followers}</small><br/>
											<small>Facebook Likes: {this.state.data.community_data.facebok_likes}</small><br/>
											<small>Reddit Active Accounts (48h): {this.state.data.community_data.reddit_accounts_active_48h}</small><br/>
											<small>Reddit Subscribers: {this.state.data.community_data.reddit_subscribers}</small><br/>
											<small>Telegram channel user count: {this.state.data.community_data.telegram_channel_user_count}</small><br/>
											
											<h3>Developer</h3>
											
											
										</div>
										:
										'Loading data..'
										
									}
		     				</div>
		    					<div className="modal-footer">
		       					<button type="button" id="closeGeckoModal" className="btn btn-secondary" data-bs-dismiss="modal">Close</button>
		       					<button type="button" onClick={this.handleDelete} type="submit" className="btn btn-danger">Delete coin</button>
		     					</div>
		   				</div>
		 				</div>
				</div>
	   	);
	}
}

export default CoinGeckoInfoModal;